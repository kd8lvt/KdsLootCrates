package com.kd8lvt.item;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class ShinyPowderItem extends UsableOnPokemon {
    public ShinyPowderItem() {
        super(new Item.Properties().rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE,true));
    }

    @Override
    void doTheThing(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        entity.getPokemon().setShiny(true);
        stack.consumeAndReturn(1, player);
    }
    
    @Override
    public boolean shouldUse(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        return (                                                   //Only use when the given pokemon...
                !entity.getPokemon().getShiny()                    //is not shiny
                && entity.getOwner() != null                       //and has an owner
                && entity.getOwner().getUUID() == player.getUUID() //who is the player using the item
        );
    }
}
