package com.kd8lvt.block;

import com.kd8lvt.init.ModComponents;
import com.kd8lvt.item.KeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class CrateBlock extends Block {
    public CrateBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).noLootTable());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.getItemInHand(interactionHand).getItem() instanceof KeyItem item) {
            item.giveLoot(player,level,itemStack.get(ModComponents.LOOTABLE_TABLE));
            return ItemInteractionResult.CONSUME;
        }
        return super.useItemOn(itemStack, blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
