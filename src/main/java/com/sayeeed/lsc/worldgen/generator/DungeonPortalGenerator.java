package com.sayeeed.lsc.worldgen.generator;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.sayeeed.lsc.init.LSCStructures;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.pool.LegacySinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;

/**
 * 
 * @author sayeeed
 *
 */
public class DungeonPortalGenerator 
{
	private static final Identifier DUNGEON_PORTAL = Reference.id("dungeon/portals");
	
	static
	{
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        DUNGEON_PORTAL,
                        new Identifier("empty"),
                        ImmutableList.of(
                                new Pair<>(new LegacySinglePoolElement(Reference.MODID + ":dungeons/portals/overworld_portal_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
	}
	
	public static void addPieces(ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos pos, List<StructurePiece> pieces, ChunkRandom random) 
	{
        StructurePoolBasedGenerator.addPieces(DUNGEON_PORTAL, 7, DungeonPortalGenerator.Piece::new, chunkGenerator, structureManager, pos, pieces, random, true, true);
        System.out.println("generating overworld dungeon portal");
    }
	
	public static class Piece extends PoolStructurePiece 
	{
        public Piece(StructureManager manager, CompoundTag tag) 
        {
            super(manager, tag, LSCStructures.DUNGEON_PORTAL_PIECE);
        }

        public Piece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) 
        {
            super(LSCStructures.DUNGEON_PORTAL_PIECE, structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
        }
    }
}
