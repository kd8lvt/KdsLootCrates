package com.kd8lvt.datagen.lang;

import com.kd8lvt.init.ModBlocks;
import com.kd8lvt.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static com.kd8lvt.KdsLootCrates.MOD_NAME;

public class EnglishGenerator extends FabricLanguageProvider {
    private static final ArrayList<String> GENERATED_KEYS = new ArrayList<>();
    private static TranslationBuilder builder;
    public EnglishGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        builder = translationBuilder;
        builder.add(ModBlocks.CRATE,"Loot Crate");
        builder.add(Objects.requireNonNull(BuiltInRegistries.CREATIVE_MODE_TAB.getKey(ModItems.CREATIVE_TAB)).toLanguageKey(),MOD_NAME);
        for (Item item : ModItems.ITEMS) {
            tryGenTitlecase(item);
        }
    }

    public static void tryGenTitlecase(Item item) {
        ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
        String langKey = rl.toLanguageKey("item");
        if (GENERATED_KEYS.contains(langKey)) return;
        String titleCased = toTitleCase(rl.getPath().replaceAll("_"," "));
        LOGGER.info("Generating titlecase-ified English translation for {} ({})", titleCased, langKey);
        add(langKey, titleCased);
    }

    public static void add(String key, String translation) {
        try {
            builder.add(key, translation);
        } catch (Exception e) {
            LOGGER.warn("Error while trying to add translation. Woops.");
            LOGGER.error(e.toString());
        }
        GENERATED_KEYS.add(key);
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        String target_str = input;
        if (input.indexOf(".") > 0) target_str = input.split("\\.")[input.split("\\.").length-1];

        for (char c:target_str.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
