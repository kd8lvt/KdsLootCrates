package com.kd8lvt;

import com.bedrockk.molang.runtime.MoLangRuntime;
import com.cobblemon.mod.common.util.MoLangExtensionsKt;
import com.kd8lvt.init.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KdsLootCrates implements ModInitializer {
	public static final String MOD_ID = "kds_loot_crates";
	public static final String MOD_NAME = "Kd's Loot Crates";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModRegistry.all();
	}

	public static void runMolang(MoLangRuntime runtime, String expression) {
		runtime.execute(MoLangExtensionsKt.asExpression(expression));
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID,path);
	}
}