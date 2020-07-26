package com.sayeeed.lsc.block.entity;

import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.init.LSCBlocks;
import com.sayeeed.lsc.init.LSCDimensions;
import com.sayeeed.lsc.util.Reference;
import com.sayeeed.lsc.worldgen.dimension.DungeonPlacementHandler;
import com.sayeeed.lsc.worldgen.structure.processor.JigsawStructureProcessor;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
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
					ServerWorld serverWorld = (ServerWorld) player.getEntityWorld();
					ServerWorld dungeonWorld = serverWorld.getServer().getWorld(LSCDimensions.DUNGEON_DIMENSION);
					
					if (!hasDungeonGenerated)
					{	
						BlockPos pos = findDungeonLocation();
						LootSlashConquer.LOGGER.info("Generating dungeon @ " + pos);
										
						// generating naturally thru structure starts.						

						//DungeonGenerator.addPieces(dungeonWorld.getChunkManager().getChunkGenerator(), dungeonWorld.getStructureManager(), pos, LSCStructures.DUNGEON.method_28622(chunkGenerator, biomeSource, structureManager, l, chunkPos, biome, i, structureConfig), new ChunkRandom());
						
						// generating with custom processor
						dungeonWorld.getStructureManager().getStructure(Reference.id("dungeons/starting_rooms/starting_room_1")).place(dungeonWorld.getWorld(), pos, new StructurePlacementData().addProcessor(new JigsawStructureProcessor()), dungeonWorld.getRandom());
						
						//hasDungeonGenerated = true;
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
			
			FabricDimensions.teleport(player, dungeonWorld, DungeonPlacementHandler.enter(this.getPos()));
		}
		else
		{
			LootSlashConquer.LOGGER.warn("Teleporting to Dungeon Dimension failed due to the player not being in the Overworld.");
		}
	}
	
	private BlockPos findDungeonLocation()
	{
		boolean foundLocation = false;
		int tries = 0;
		int x = 0;
		int z = 0;
		int xOffset = 0;
		int zOffset = 0;
		
		while (!foundLocation)
		{	
			x = this.getPos().getX() / 128;
			z = this.getPos().getZ() / 128;
			x = 1 + x + xOffset;
			z = 1 + z + zOffset;
			
			if (x % 2 == 0) x++;
			if (z % 2 == 0) z++;
			
			x *= 128;
			z *= 128;
			
			if (!world.getBlockState(new BlockPos(x, 100, z)).isOf(Blocks.STONE_BRICKS))
			{
				foundLocation = true;
			}
			else
			{
				if (tries % 2 == 0) xOffset += 2;
				else zOffset += 2;
			}
			
			tries++;
		}
		
		return new BlockPos(x, 100, z);
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) 
	{
	      super.toTag(tag);
	      
	      tag.putBoolean("HasDungeonGenerated", hasDungeonGenerated);
	      tag.putBoolean("IsDungeonInProgress", isDungeonInProgress);
	      tag.putInt("PartyCount", partyCount);
	      
	      return tag;
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag tag) 
	{
		   super.fromTag(state, tag);
		   
		   hasDungeonGenerated = tag.getBoolean("HasDungeonGenerated");
		   isDungeonInProgress = tag.getBoolean("IsDungeonInProgress");
		   partyCount = tag.getInt("PartyCount");
	}
}
