package com.kd8lvt.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import static com.kd8lvt.KdsLootCrates.id;

public class ModComponents {
    public static DataComponentType<ResourceLocation> LOOTABLE_TABLE;
    public static void all() {
        LOOTABLE_TABLE = Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                id("lootable_table"),
                DataComponentType.<ResourceLocation>builder()
                        .persistent(ResourceLocation.CODEC)
                        .networkSynchronized(ResourceLocation.STREAM_CODEC)
                        .build()
        );
    }
}
