package com.sayeeed.lsc.init;

import com.sayeeed.lsc.block.DungeonControllerBlock;
import com.sayeeed.lsc.block.DungeonPortalBlock;
import com.sayeeed.lsc.util.Reference;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

/**
 * 
 * @author sayeeed
 *
 */
public class LSCBlocks 
{
	public static final Block DUNGEON_PORTAL = new DungeonPortalBlock(FabricBlockSettings.of(Material.METAL));
	public static final Block DUNGEON_CONTROLLER = new DungeonControllerBlock(FabricBlockSettings.of(Material.METAL));
	
	public static void registerBlocks()
	{
		Registry.register(Registry.BLOCK, Reference.id("dungeon_portal"), DUNGEON_PORTAL);
		Registry.register(Registry.ITEM, Reference.id("dungeon_portal"), new BlockItem(DUNGEON_PORTAL, new Item.Settings()));
		Registry.register(Registry.BLOCK, Reference.id("dungeon_controller"), DUNGEON_CONTROLLER);
		Registry.register(Registry.ITEM, Reference.id("dungeon_controller"), new BlockItem(DUNGEON_CONTROLLER, new Item.Settings()));
	}
}