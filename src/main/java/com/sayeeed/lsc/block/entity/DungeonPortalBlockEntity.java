package com.sayeeed.lsc.block.entity;

import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.dungeon.DungeonGenerator;
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
import net.minecraft.util.math.BlockPos;
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
	private BlockPos dungeonPos;
	private boolean isDungeonInProgress;
	private int partyCount;
	
	public DungeonPortalBlockEntity() 
	{
		super(LSCBlocks.DUNGEON_PORTAL_BLOCK_ENTITY);
		hasDungeonGenerated = false;
		dungeonPos = this.getPos();
		isDungeonInProgress = false;
		partyCount = 0;
	}

	@Override
	public void tick() 
	{
		if (!this.getWorld().isClient())
		{
			PlayerEntity player = this.getWorld().getClosestPlayer(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 5, false);
			
			if (player != null && !isDungeonInProgress && partyCount <= 4)
			{
				if (player.isSubmergedInWater() && (player.getBlockPos().getY() > this.getPos().getY() || player.getBlockPos().getY() <= this.getPos().getY() + 4))
				{
					ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
					
					if (!hasDungeonGenerated)
					{	
						dungeonPos = DungeonGenerator.generateDungeon(player, this);
						hasDungeonGenerated = true;
						this.markDirty();
					}
					
					teleportPlayerToDungeon(player, serverWorld);
				}
			}
		}
	}
	
	private void teleportPlayerToDungeon(PlayerEntity player, ServerWorld currentWorld)
	{
		if (currentWorld.getRegistryKey() == World.OVERWORLD)
		{
			ServerWorld dungeonWorld = currentWorld.getServer().getWorld(LSCDimensions.DUNGEON_DIMENSION);
			
			FabricDimensions.teleport(player, dungeonWorld, DungeonPlacementHandler.enter(dungeonPos.add(5, 2, 5)));
			
			partyCount++;
			this.markDirty();
		}
		else
		{
			LootSlashConquer.LOGGER.warn("Teleporting to Dungeon Dimension failed due to the player not being in the Overworld.");
		}
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) 
	{
	      super.toTag(tag);
	      
	      tag.putBoolean("HasDungeonGenerated", hasDungeonGenerated);
	      tag.putInt("DungeonX", dungeonPos.getX());
	      tag.putInt("DungeonY", dungeonPos.getY());
	      tag.putInt("DungeonZ", dungeonPos.getZ());
	      tag.putBoolean("IsDungeonInProgress", isDungeonInProgress);
	      tag.putInt("PartyCount", partyCount);
	      
	      return tag;
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag tag) 
	{
		   super.fromTag(state, tag);
		   
		   hasDungeonGenerated = tag.getBoolean("HasDungeonGenerated");
		   dungeonPos = new BlockPos(tag.getInt("DungeonX"), tag.getInt("DungeonY"), tag.getInt("DungeonZ"));
		   isDungeonInProgress = tag.getBoolean("IsDungeonInProgress");
		   partyCount = tag.getInt("PartyCount");
	}
}
