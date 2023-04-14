package io.github.floder91.betterfabricdrawers.item;

import io.github.floder91.betterfabricdrawers.block.base.DrawerInteractionHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class LockItem extends Item {
    public LockItem(Settings settings) {
        super(settings);
    }
    
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() instanceof DrawerInteractionHandler lockable) {
            return lockable.toggleLock(context.getWorld().getBlockState(context.getBlockPos()), context.getWorld(), context.getBlockPos(), context.getHitPos(), context.getSide());
        }
        return ActionResult.PASS;
    }
}
