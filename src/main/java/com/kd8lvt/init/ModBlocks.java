package com.kd8lvt.init;

import com.kd8lvt.KdsLootCrates;
import com.kd8lvt.block.CrateBlock;
import java.util.ArrayList;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModBlocks {
    public static ArrayList<Block> BLOCKS = new ArrayList<>();
    public static CrateBlock CRATE;
    public static void all() {
        CRATE = blockWithItem(new CrateBlock(),"crate");
    }

    public static <T extends Block> T blockWithItem(T block,String id) {
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.BLOCK, KdsLootCrates.id(id), block);
        ModItems.register(id,blockItem);
        BLOCKS.add(block);
        return block;
    }
}
