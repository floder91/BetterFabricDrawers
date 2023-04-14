package io.github.floder91.betterfabricdrawers.misc;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import io.github.floder91.betterfabricdrawers.block.entity.DrawerBlockEntity;
import io.github.floder91.betterfabricdrawers.registry.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static io.github.floder91.betterfabricdrawers.BetterFabricDrawers.id;

public class DrawerContentsLootFunction extends ConditionalLootFunction {
    
    private static final LootFunctionType TYPE = new LootFunctionType(new Serializer());
    
    protected DrawerContentsLootFunction(LootCondition[] conditions) {
        super(conditions);
    }
    
    public static void register() {
        Registry.register(Registries.LOOT_FUNCTION_TYPE, id("drawer_contents"), TYPE);
    }
    
    public static ConditionalLootFunction.Builder<?> builder() {
        return builder(DrawerContentsLootFunction::new);
    }
    
    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {
        if (stack.isEmpty()) return stack;
        if (context.get(LootContextParameters.BLOCK_ENTITY) instanceof DrawerBlockEntity drawer && !drawer.isEmpty()) {
            var nbt = new NbtCompound();
            drawer.writeNbt(nbt);
            var itemNbt = BlockItem.getBlockEntityNbt(stack);
            if (itemNbt == null) {
                itemNbt = nbt;
            } else {
                itemNbt.copyFrom(nbt);
            }
            BlockItem.setBlockEntityNbt(stack, ModBlocks.DRAWER_BLOCK_ENTITY, itemNbt);
        }
        return stack;
    }
    
    @Override
    public LootFunctionType getType() {
        return TYPE;
    }
    
    public static class Serializer extends ConditionalLootFunction.Serializer<DrawerContentsLootFunction> {
        @Override
        public DrawerContentsLootFunction fromJson(JsonObject json, JsonDeserializationContext context, LootCondition[] conditions) {
            return new DrawerContentsLootFunction(conditions);
        }
    }
}
