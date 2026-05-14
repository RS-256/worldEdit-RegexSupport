package com.rs256.wers.core;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.mask.BlockTypeMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.internal.registry.InputParser;
import com.sk89q.worldedit.util.formatting.text.TextComponent;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

public class RegexBlockMaskParser extends InputParser<Mask> {
    public RegexBlockMaskParser(WorldEdit worldEdit) {
        super(worldEdit);
    }

    @Override
    public Mask parseFromInput(String input, ParserContext context) throws InputParseException {
        List<Identifier> ids;
        try {
            ids = RegistryIdMatcher.findMatchingIds(BuiltInRegistries.BLOCK, input);
        } catch (PatternSyntaxException e) {
            if (looksLikeRegex(input)) {
                throw new InputParseException(TextComponent.of("Invalid block id regex: " + input), e);
            }
            return null;
        }

        if (ids.isEmpty()) {
            return null;
        }

        Set<BlockType> blocks = new LinkedHashSet<>();
        for (Identifier id : ids) {
            BlockType block = BlockTypes.get(id.toString());
            if (block != null) {
                blocks.add(block);
            }
        }

        if (blocks.isEmpty()) {
            return null;
        }

        return new BlockTypeMask(context.requireExtent(), blocks);
    }

    private static boolean looksLikeRegex(String input) {
        for (int i = 0; i < input.length(); i++) {
            if ("\\.^$*+?()[]{}|".indexOf(input.charAt(i)) >= 0) {
                return true;
            }
        }
        return false;
    }
}
