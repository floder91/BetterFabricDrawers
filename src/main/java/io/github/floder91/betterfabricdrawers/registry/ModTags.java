package io.github.floder91.betterfabricdrawers.registry;

import io.github.floder91.betterfabricdrawers.BetterFabricDrawers;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> DRAWERS = TagKey.of(RegistryKeys.ITEM, BetterFabricDrawers.id("drawers"));
        public static final TagKey<Item> UPGRADES = TagKey.of(RegistryKeys.ITEM, BetterFabricDrawers.id("upgrade"));
    }
    
    public static class BlockTags {
        public static final TagKey<Block> DRAWERS = TagKey.of(RegistryKeys.BLOCK, BetterFabricDrawers.id("drawers"));
        public static final TagKey<Block> NETWORK_COMPONENTS = TagKey.of(RegistryKeys.BLOCK, BetterFabricDrawers.id("network_components"));
    }
}
