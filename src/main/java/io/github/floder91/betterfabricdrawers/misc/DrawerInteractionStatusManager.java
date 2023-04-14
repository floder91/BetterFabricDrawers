package io.github.floder91.betterfabricdrawers.misc;

import io.github.floder91.betterfabricdrawers.config.CommonConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;

import java.util.WeakHashMap;

public final class DrawerInteractionStatusManager {
    private DrawerInteractionStatusManager(){}
    
    // Using ThreadLocal to separate server and client
    private static final ThreadLocal<WeakHashMap<PlayerEntity, Interaction>> INSERTIONS = ThreadLocal.withInitial(WeakHashMap::new);
    
    /**
     * Provides whether the player should insert one or all stacks and updates internal counters to match that. Ensures that double clicks only cause multi-stack insertion when the clicks are on the same slot.
     * @param player The player performing the insertion
     * @param pos The position of the drawer
     * @param slot The slot that was interacted with
     * @return Wherther there shouldn be a multi-stack insertion.
     */
    public static boolean getAndResetInsertStatus(PlayerEntity player, BlockPos pos, int slot) {
        var timestamp = player.getWorld().getTime();
        var interaction = INSERTIONS.get().remove(player);
        if (interaction != null && interaction.pos.equals(pos) && timestamp - interaction.timestamp < CommonConfig.HANDLE.get().insertAllTime() && interaction.slot == slot)
            return true;
            
        INSERTIONS.get().put(player, new Interaction(timestamp, pos.toImmutable(), slot));
        return false;
    }
    
    private record Interaction(long timestamp, BlockPos pos, int slot) {}
}
