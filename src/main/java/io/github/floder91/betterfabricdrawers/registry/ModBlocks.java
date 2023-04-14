package io.github.floder91.betterfabricdrawers.registry;

import io.github.floder91.betterfabricdrawers.BetterFabricDrawers;
import io.github.floder91.betterfabricdrawers.block.AccessPointBlock;
import io.github.floder91.betterfabricdrawers.block.ConnectorBlock;
import io.github.floder91.betterfabricdrawers.block.DrawerBlock;
import io.github.floder91.betterfabricdrawers.block.ShadowDrawerBlock;
import io.github.floder91.betterfabricdrawers.block.entity.DrawerBlockEntity;
import io.github.floder91.betterfabricdrawers.block.entity.ShadowDrawerBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    public static final DrawerBlock SINGLE_DRAWER = new DrawerBlock(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD), 1);
    public static final DrawerBlock DOUBLE_DRAWER = new DrawerBlock(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD), 2);
    public static final DrawerBlock QUAD_DRAWER = new DrawerBlock(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD), 4);
    public static final ConnectorBlock CONNECTOR = new ConnectorBlock(FabricBlockSettings.of(Material.WOOD).strength(2f, 3f).sounds(BlockSoundGroup.WOOD));
    public static final AccessPointBlock ACCESS_POINT = new AccessPointBlock(FabricBlockSettings.of(Material.STONE).strength(3f, 9f).sounds(BlockSoundGroup.STONE));
    public static final ShadowDrawerBlock SHADOW_DRAWER = new ShadowDrawerBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW).strength(3f, 9f).sounds(BlockSoundGroup.STONE));
    
    public static final BlockEntityType<DrawerBlockEntity> DRAWER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(DrawerBlockEntity::new, SINGLE_DRAWER, DOUBLE_DRAWER, QUAD_DRAWER).build();
    public static final BlockEntityType<ShadowDrawerBlockEntity> SHADOW_DRAWER_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(ShadowDrawerBlockEntity::new, SHADOW_DRAWER).build();
    
    public static void register() {
        Registry.register(Registries.BLOCK, BetterFabricDrawers.id("single_drawer"), SINGLE_DRAWER);
        Registry.register(Registries.BLOCK, BetterFabricDrawers.id("double_drawer"), DOUBLE_DRAWER);
        Registry.register(Registries.BLOCK, BetterFabricDrawers.id("quad_drawer"), QUAD_DRAWER);
        Registry.register(Registries.BLOCK, BetterFabricDrawers.id("connector"), CONNECTOR);
        Registry.register(Registries.BLOCK, BetterFabricDrawers.id("access_point"), ACCESS_POINT);
        Registry.register(Registries.BLOCK, BetterFabricDrawers.id("shadow_drawer"), SHADOW_DRAWER);
        
        Registry.register(Registries.BLOCK_ENTITY_TYPE, BetterFabricDrawers.id("drawer"), DRAWER_BLOCK_ENTITY);
        Registry.register(Registries.BLOCK_ENTITY_TYPE, BetterFabricDrawers.id("shadow_drawer"), SHADOW_DRAWER_BLOCK_ENTITY);
    }
}
