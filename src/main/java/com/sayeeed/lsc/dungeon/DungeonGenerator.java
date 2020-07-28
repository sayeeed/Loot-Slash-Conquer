package com.sayeeed.lsc.dungeon;

import java.util.Iterator;
import java.util.List;

import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.block.entity.DungeonPortalBlockEntity;
import com.sayeeed.lsc.init.LSCDimensions;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.JigsawBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.Structure.StructureBlockInfo;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 
 * @author sayeeed
 * 
 * Dungeon generator.
 * 
 * This doesn't go through vanilla at all as there's no need to generate these via worldgen. To adapt this,
 * make your own structure feature and do it that way.
 *
 */
public class DungeonGenerator 
{	
	/**
	 * Generates the dungeon from .nbt and run data handlers to generate jigsaw blocks and handle loot.
	 * @param player
	 * @param portalEntity
	 */
	public static BlockPos generateDungeon(PlayerEntity player, DungeonPortalBlockEntity portalEntity)
	{
		BlockPos dungeonPos = findDungeonLocation(player.getEntityWorld(), portalEntity.getPos());
		LootSlashConquer.LOGGER.info("Generating dungeon @ " + dungeonPos);
		
		ServerWorld serverWorld = (ServerWorld) portalEntity.getWorld();
		ServerWorld dungeonWorld = serverWorld.getServer().getWorld(LSCDimensions.DUNGEON_DIMENSION);
		
		Structure structure = dungeonWorld.getStructureManager().getStructure(Reference.id("dungeons/dungeon_initializer"));
		StructurePlacementData placement = new StructurePlacementData();
		structure.place(dungeonWorld.getWorld(), dungeonPos, placement, dungeonWorld.getRandom());
		List<StructureBlockInfo> structureBlockInfos = structure.getInfosForBlock(dungeonPos, placement, Blocks.JIGSAW);
		Iterator<StructureBlockInfo> iterator = structureBlockInfos.iterator();
		
		while (iterator.hasNext())
		{
			StructureBlockInfo info = iterator.next();
			JigsawBlockEntity jigsawEntity = (JigsawBlockEntity) dungeonWorld.getBlockEntity(info.pos);
			
			if (jigsawEntity != null)
			{
				LootSlashConquer.LOGGER.info("jigsaw generating.");
				jigsawEntity.generate(dungeonWorld, 15, false);
				
			}
		}
		
		return dungeonPos;
	}
	
	/**
	 * Find the closest dungeon plot in the dungeon dimension to the portal's position. If already taken, generate in a plot next to the used one.
	 * @param world
	 * @param portalPos
	 * @return
	 */
	private static BlockPos findDungeonLocation(World world, BlockPos portalPos)
	{
		boolean foundLocation = false;
		int tries = 0;
		int x = 0;
		int z = 0;
		int xOffset = 0;
		int zOffset = 0;
		
		while (!foundLocation)
		{	
			x = portalPos.getX() / 128;
			z = portalPos.getZ() / 128;
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
	
	public static void handleDataBlocks(World world, StructureBlockInfo dataBlockInfo)
	{
		LootSlashConquer.LOGGER.info("Handling Data Blocks!");
		
		if (world.getBlockState(dataBlockInfo.pos.down(1)).isOf(Blocks.CHEST))
		{
			@SuppressWarnings("unused")
			ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(dataBlockInfo.pos.down(1));
			
			switch (dataBlockInfo.tag.getString("metadata"))
			{
				case "chest":
					// set loot table
			}
		}
	}
}
