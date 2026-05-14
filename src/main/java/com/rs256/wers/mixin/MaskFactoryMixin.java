package com.rs256.wers.mixin;

import com.rs256.wers.core.RegexBlockMaskParser;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extension.factory.MaskFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MaskFactory.class, remap = false)
public abstract class MaskFactoryMixin {
    @Inject(method = "<init>", at = @At("RETURN"), remap = false)
    private void wers$registerRegexBlockMaskParser(WorldEdit worldEdit, CallbackInfo info) {
        ((MaskFactory) (Object) this).register(new RegexBlockMaskParser(worldEdit));
    }
}
