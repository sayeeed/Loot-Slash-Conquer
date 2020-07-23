package com.sayeeed.lsc.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class DungeonPortalBlock extends Block implements BlockEntityProvider
{
	public DungeonPortalBlock(Settings settings) 
	{
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) 
	{
		return new DungeonPortalBlockEntity();
	}
}
