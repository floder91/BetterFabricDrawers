package io.github.floder91.betterfabricdrawers.block.base;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface CreativeBreakBlocker {
    boolean shouldBlock(World world, BlockPos pos, Direction direction);
}
