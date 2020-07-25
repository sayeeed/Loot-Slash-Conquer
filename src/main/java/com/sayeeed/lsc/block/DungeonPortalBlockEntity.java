package com.sayeeed.lsc.block;

import com.sayeeed.lsc.init.LSCBlocks;
import com.sayeeed.lsc.init.LSCDimensions;
import com.sayeeed.lsc.worldgen.dimension.DungeonPlacementHandler;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;

/**
 * 
 * @author sayeeed
 *
 */
@SuppressWarnings("deprecation")
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
		
		if (!this.getWorld().isClient())
		{
			PlayerEntity player = this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 5, false);
			
			if (player != null && !isDungeonInProgress && partyCount <= 4)
			{
				if (player.isSubmergedInWater() && (player.getBlockPos().getY() > this.getPos().getY() || player.getBlockPos().getY() <= this.getPos().getY() + 4))
				{
					if (!hasDungeonGenerated)
					{
						// gen dungeon
					}
					
					teleportPlayerToDungeon(player);
				}
			}
		}
	}
	
	private void teleportPlayerToDungeon(PlayerEntity player)
	{
		ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
		
		if (serverWorld.getRegistryKey() == World.OVERWORLD)
		{
			ServerWorld dungeonWorld = serverWorld.getServer().getWorld(LSCDimensions.DUNGEON_DIMENSION);
			
			FabricDimensions.teleport(player, dungeonWorld, DungeonPlacementHandler.enter(this.getPos()));
		}
		else
		{
			// teleporting fails
		}
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