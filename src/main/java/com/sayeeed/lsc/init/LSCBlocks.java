package com.sayeeed.lsc.init;

import com.sayeeed.lsc.block.DungeonPortalBlock;
import com.sayeeed.lsc.block.DungeonPortalBlockEntity;
import com.sayeeed.lsc.util.Reference;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
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
	public static final Block DUNGEON_PORTAL = new DungeonPortalBlock(FabricBlockSettings.of(Material.METAL).hardness(-1.0F));
	
	public static BlockEntityType<DungeonPortalBlockEntity> DUNGEON_PORTAL_BLOCK_ENTITY;
	
	public static void registerBlocks()
	{
		Registry.register(Registry.BLOCK, Reference.id("dungeon_portal"), DUNGEON_PORTAL);
		Registry.register(Registry.ITEM, Reference.id("dungeon_portal"), new BlockItem(DUNGEON_PORTAL, new Item.Settings()));
		
		DUNGEON_PORTAL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "lsc:dungeon_portal", BlockEntityType.Builder.create(DungeonPortalBlockEntity::new, DUNGEON_PORTAL).build(null));
	}
}