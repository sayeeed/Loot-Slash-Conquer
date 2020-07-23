package com.sayeeed.lsc.block;

import com.sayeeed.lsc.init.LSCBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

/**
 * 
 * @author sayeeed
 *
 */
public class DungeonPortalBlockEntity extends BlockEntity implements Tickable
{
	private boolean hasDungeonGenerated;
	private boolean isDungeonInProgress;
	private int partyCount;
	
	public DungeonPortalBlockEntity() 
	{
		super(LSCBlocks.DUNGEON_PORTAL_BLOCK_ENTITY);
		hasDungeonGenerated = false;
		isDungeonInProgress = false;
		partyCount = 0;
	}

	@Override
	public void tick() 
	{
		// teleport player into the dungeon area. if dungeon has not been generate, generate dungeon.
		// increase player count and begin dungeon countdown.
		
		PlayerEntity player = this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 5, false);
		
		if (player != null && partyCount <= 4)
		{
			if (player.isTouchingWater() && (player.getBlockPos().getY() > this.getPos().getY() || player.getBlockPos().getY() <= this.getPos().getY() + 4));
			{
				if (!hasDungeonGenerated)
				{
					// gen dungeon
				}
			}
		}
	}
	
	private void teleportPlayer()
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
