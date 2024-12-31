package com.kd8lvt.item;

import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.IVs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.atomic.AtomicBoolean;

public class GoldBottlecapItem extends BottlecapItem {
    public GoldBottlecapItem() {
        super();
    }

    @Override
    void doTheThing(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        Stats.getEntries().forEach((stat)-> {
            entity.getPokemon().getIvs().set(stat, 31);
        });
    }

    @Override
    public boolean shouldUse(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        IVs ivs = entity.getPokemon().getIvs();
        AtomicBoolean allIvsMax = new AtomicBoolean(true);
        ivs.forEach(entry -> {
            if (allIvsMax.get()) return;
            if (entry.getValue() < 31) allIvsMax.set(false);
        });
        return super.shouldUse(entity, player, stack, interactionHand) && !allIvsMax.get();
    }
}
