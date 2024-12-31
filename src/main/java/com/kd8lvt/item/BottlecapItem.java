package com.kd8lvt.item;

import com.cobblemon.mod.common.api.pokemon.stats.Stat;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.IVs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class BottlecapItem extends UsableOnPokemon {
    public BottlecapItem() {
        super(new Properties().stacksTo(16));
    }

    @Override
    void doTheThing(PokemonEntity entity, Player player, ItemStack stack, InteractionHand interactionHand) {
        AtomicReference<ArrayList<Stat>> lowStats = new AtomicReference<>(new ArrayList<>());
        entity.getPokemon().getIvs().forEach(entry->{
            if (entry.getValue() < 31) {
                ArrayList<Stat> tmp = lowStats.get();
                tmp.add(entry.getKey());
                lowStats.set(tmp); //hate
            }
        });
        Stat statToMaximize = lowStats.get().get(entity.level().random.nextInt(0,lowStats.get().size()));
        entity.getPokemon().getIvs().set(statToMaximize,31);
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
