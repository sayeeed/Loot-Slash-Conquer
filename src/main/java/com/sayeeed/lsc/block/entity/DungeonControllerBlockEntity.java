package com.sayeeed.lsc.block.entity;

import com.sayeeed.lsc.init.LSCBlockEntities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

/**
 * 
 * @author sayeeed
 *
 */
public class DungeonControllerBlockEntity extends BlockEntity implements Tickable
{
	public DungeonControllerBlockEntity() 
	{
		super(LSCBlockEntities.DUNGEON_CONTROLLER);
	}

	@Override
	public void tick() 
	{
		
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) 
	{
	      super.toTag(tag);
	      
	      return tag;
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag tag) 
	{
		   super.fromTag(state, tag);
	}
}
