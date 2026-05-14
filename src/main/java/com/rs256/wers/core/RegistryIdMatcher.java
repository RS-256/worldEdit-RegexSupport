package com.rs256.wers.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public final class RegistryIdMatcher {
    private RegistryIdMatcher() {
    }

    public static <T> List<Identifier> findMatchingIds(Registry<T> registry, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Set<Identifier> matches = new LinkedHashSet<>();
        addMatches(matches, registry, pattern);
        return new ArrayList<>(matches);
    }

    private static void addMatches(Set<Identifier> matches, Registry<?> registry, Pattern pattern) {
        for (Identifier id : registry.keySet()) {
            if (pattern.matcher(id.toString()).matches()) {
                matches.add(id);
            }
        }
    }
}
