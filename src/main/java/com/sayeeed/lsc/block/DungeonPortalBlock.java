package com.sayeeed.lsc.block;

import com.sayeeed.lsc.block.entity.DungeonPortalBlockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

/**
 * 
 * @author sayeeed
 *
 */
public class DungeonPortalBlock extends Block implements BlockEntityProvider
{
	public DungeonPortalBlock(FabricBlockSettings settings) 
	{
		super(settings);
		settings.hardness(-1F);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) 
	{
		return new DungeonPortalBlockEntity();
	}
}
