package com.sayeeed.lsc.worldgen.structure.processor;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.init.LSCStructures;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.JigsawBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure.StructureBlockInfo;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

/**
 * 
 * @author sayeeed
 *
 */
public class JigsawStructureProcessor extends StructureProcessor
{
	public static final JigsawStructureProcessor INSTANCE = new JigsawStructureProcessor();
	public static final Codec<JigsawStructureProcessor> CODEC = Codec.unit(() -> {
	      return INSTANCE;
	   });
	
	@Override
	public StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, StructureBlockInfo structureBlockInfo, StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) 
	{
		BlockState blockstate = structureBlockInfo2.state;
		
		if (!worldView.isClient() && blockstate.isOf(Blocks.JIGSAW))
		{
			ServerWorld serverWorld = (ServerWorld) worldView;
			JigsawBlockEntity jigsaw = (JigsawBlockEntity) worldView.getBlockEntity(structureBlockInfo2.pos);
			
			LootSlashConquer.LOGGER.info("jigsaw:   " + jigsaw);
			LootSlashConquer.LOGGER.info("blockstate:   " + blockstate);
			
			if (jigsaw != null)
			{
				jigsaw.generate(serverWorld, 7, false);
			}
		}
		
		return structureBlockInfo2;
	}

	@Override
	protected StructureProcessorType<?> getType()
	{
		return LSCStructures.JIGSAW_PROCESSOR;
	}
	
	private static final Identifier START = Reference.id("starting_pool");
	private static final Identifier HALLWAYS = Reference.id("hallways");
	private static final Identifier ROOMS = Reference.id("rooms");
	
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
