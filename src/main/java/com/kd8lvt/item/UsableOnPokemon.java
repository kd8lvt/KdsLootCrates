package com.kd8lvt.item;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class UsableOnPokemon extends Item {
    public UsableOnPokemon(Properties properties) {super(properties);}

    public boolean shouldUse(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        return (!entity.isDeadOrDying() && entity.isOwnedBy(player) && entity.isAlive());
    }

    abstract void doTheThing(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand);

    boolean shouldDecrement(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        return this.shouldUse(entity,player,stack,interactionHand);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (!(livingEntity instanceof PokemonEntity pokemon)) return super.interactLivingEntity(stack, player, livingEntity, interactionHand);
        if (this.shouldUse(pokemon,player,stack,interactionHand)) this.doTheThing(pokemon,player,stack,interactionHand);
        if (this.shouldDecrement(pokemon,player,stack,interactionHand)) stack.consumeAndReturn(1,player);
        return super.interactLivingEntity(stack, player, livingEntity, interactionHand);
    }
}
