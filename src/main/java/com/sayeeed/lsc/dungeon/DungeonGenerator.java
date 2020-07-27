package com.sayeeed.lsc.dungeon;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
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
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
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
	private static final Identifier START = Reference.id("starting_pool");
	private static final Identifier HALLWAYS = Reference.id("hallways");
	private static final Identifier ROOMS = Reference.id("rooms");
	
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
		
		Structure structure = dungeonWorld.getStructureManager().getStructure(Reference.id("dungeons/starting_rooms/starting_room_1"));
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
				jigsawEntity.generate(dungeonWorld, 7, false);
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
	
	static
	{
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        START,
                        new Identifier("empty"),
                        ImmutableList.of(
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/starting_rooms/starting_room_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
		
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        HALLWAYS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_straight_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
		
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        ROOMS,
                        new Identifier("empty"),
                        ImmutableList.of(
                                Pair.of(new SinglePoolElement("lsc:dungeons/rooms/simple_room_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
	}
}
