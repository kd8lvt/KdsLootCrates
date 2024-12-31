package com.kd8lvt.init;

import com.kd8lvt.item.BottlecapItem;
import com.kd8lvt.item.GoldBottlecapItem;
import com.kd8lvt.item.KeyItem;
import com.kd8lvt.item.ShinyPowderItem;
import java.util.ArrayList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import static com.kd8lvt.KdsLootCrates.MOD_ID;
import static com.kd8lvt.KdsLootCrates.id;

public final class ModItems {
    public static ArrayList<Item> ITEMS = new ArrayList<>();

    public static KeyItem KEY;

    public static ShinyPowderItem SHINY_POWDER;
    public static BottlecapItem BOTTLE_CAP;
    public static GoldBottlecapItem GOLD_BOTTLE_CAP;

    public static CreativeModeTab CREATIVE_TAB;

    public static void all() {
        KEY = register("key",new KeyItem());
        SHINY_POWDER = register("shiny_powder",new ShinyPowderItem());
        BOTTLE_CAP = register("bottle_cap",new BottlecapItem());
        GOLD_BOTTLE_CAP = register("gold_bottle_cap",new GoldBottlecapItem());

        CREATIVE_TAB = Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                id("main_tab"),
                CreativeModeTab.builder(
                        CreativeModeTab.Row.TOP,
                        CreativeModeTabs.tabs().size()
                )
                .displayItems(ModItems::buildCreativeTab)
                .build());
    }

    private static void buildCreativeTab(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        ITEMS.forEach(output::accept);
    }

    public static <T extends Item> T register(String id, T item) {
        T ret = Registry.register(BuiltInRegistries.ITEM,ResourceLocation.fromNamespaceAndPath(MOD_ID,id),item);
        ITEMS.add(ret);
        return ret;
    }
}
