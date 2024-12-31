package com.kd8lvt.datagen;

import com.kd8lvt.init.ModBlocks;
import com.kd8lvt.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public class ModelGenerator extends FabricModelProvider {
    private static FabricDataOutput output;
    private static ArrayList<Block> BLOCKS = new ArrayList<>();
    private static ArrayList<Item> ITEMS = new ArrayList<>();
    private static BlockModelGenerators blockGen;
    private static ItemModelGenerators itemGen;
    public ModelGenerator(FabricDataOutput output) {
        super(output);
        this.output=output;
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockGen=blockModelGenerators;
        for (Block block : ModBlocks.BLOCKS) {
            if (BLOCKS.contains(block)) continue;
            LOGGER.warn("Autogenerating generic cube model for {}", block.getName());
            genericBlock(block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemGen=itemModelGenerators;
        for (Item item : ModItems.ITEMS) {
            if (ITEMS.contains(item)) continue;
            LOGGER.warn("Autogenerating generic flat model for {}", item.getName(item.getDefaultInstance()));
            genericItem(item);
        }
    }

    public static void genericItem(Item item) {
        generate(item,ModelTemplates.FLAT_ITEM);
        ITEMS.add(item);
    }

    public static <T extends ModelTemplate> void generate(Item item, T template) {
        if (ITEMS.contains(item) || (item instanceof BlockItem block && BLOCKS.contains(block.getBlock()))) {
            LOGGER.warn("Already registered model for item {}!!!",item.getName(item.getDefaultInstance()));
            return;
        }
        itemGen.generateFlatItem(item,template);
        ITEMS.add(item);
        if (item instanceof BlockItem block) BLOCKS.add(block.getBlock());
    }

    public static void genericBlock(Block block) {
        ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(block);
        generate(block,TexturedModel.createAllSame(rl.withPath("block/"+rl.getPath())));
    }

    public static <B extends Block,T extends TexturedModel> void generate(B block, T template) {
        if (BLOCKS.contains(block) || (block.asItem() != Items.AIR && ITEMS.contains(block.asItem()))) {
            LOGGER.warn("Already registered model for block {}!!!",block.getName());
            return;
        }
        blockGen.createTrivialBlock(block, TexturedModel.createDefault((b)->template.getMapping(),template.getTemplate()));
        BLOCKS.add(block);
        if (block.asItem() != Items.AIR) ITEMS.add(block.asItem());
    }
}
