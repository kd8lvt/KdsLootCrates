package com.kd8lvt.item;

import com.kd8lvt.init.ModComponents;
import me.fzzyhmstrs.lootables.api.LootablesApi;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import static com.kd8lvt.KdsLootCrates.id;

public class KeyItem extends Item {
    public KeyItem() {
        super(new Properties().component(ModComponents.LOOTABLE_TABLE,id("empty")));
    }

    public void giveLoot(Player player, Level world, ResourceLocation table) {
        if (world.isClientSide) return;
        int choiceCount = 1;
        if (world.getRandom().nextFloat()<=0.02) choiceCount = 3; //2% chance for a 3-pull
        LootablesApi.supplyLootWithChoices(table,(ServerPlayer)player,player.position(),(p,vec)->{},(p,vec)->{},null,3, choiceCount);
    }
}
