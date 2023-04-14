package io.github.floder91.betterfabricdrawers.item;

import io.github.floder91.betterfabricdrawers.config.CommonConfig;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class DrawerItem extends BlockItem {
    public DrawerItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean canBeNested() {
        return CommonConfig.HANDLE.get().allowRecursion();
    }
}
