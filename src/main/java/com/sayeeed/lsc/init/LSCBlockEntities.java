package com.sayeeed.lsc.init;

import com.sayeeed.lsc.block.entity.DungeonControllerBlockEntity;
import com.sayeeed.lsc.block.entity.DungeonPortalBlockEntity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

/**
 * 
 * @author sayeeed
 *
 */
public class LSCBlockEntities 
{
	public static final BlockEntityType<DungeonPortalBlockEntity> DUNGEON_PORTAL;
	public static final BlockEntityType<DungeonControllerBlockEntity> DUNGEON_CONTROLLER;
	
	public static void registerBlockEntities() {}
	
	static
	{
		DUNGEON_PORTAL = Registry.register(Registry.BLOCK_ENTITY_TYPE, "lsc:dungeon_portal", BlockEntityType.Builder.create(DungeonPortalBlockEntity::new, LSCBlocks.DUNGEON_PORTAL).build(null));
		DUNGEON_CONTROLLER = Registry.register(Registry.BLOCK_ENTITY_TYPE, "lsc:dungeon_controller", BlockEntityType.Builder.create(DungeonControllerBlockEntity::new, LSCBlocks.DUNGEON_CONTROLLER).build(null));
	}
}
