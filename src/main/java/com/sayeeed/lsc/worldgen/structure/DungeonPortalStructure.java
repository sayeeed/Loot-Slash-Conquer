package com.sayeeed.lsc.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.sayeeed.lsc.util.Reference;
import com.sayeeed.lsc.worldgen.generator.DungeonPortalGenerator;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.VillageStructureStart;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * 
 * @author sayeeed
 *
 */
public class DungeonPortalStructure extends StructureFeature<DefaultFeatureConfig>
{
	public DungeonPortalStructure() 
	{
		super(DefaultFeatureConfig.CODEC);
	}

	@Override
	public StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() 
	{
		return DungeonPortalStructure.Start::new;
	}
	
	public static class Start extends VillageStructureStart<DefaultFeatureConfig>
	{	
		private static final Identifier BASE_POOL = Reference.id("base_pool");
		
		public Start(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) 
		{
            super(feature, chunkX, chunkZ, box, references, seed);
        }

		@Override
		public void init(ChunkGenerator chunkGenerator, StructureManager structureManager, int x, int z, Biome biome, DefaultFeatureConfig featureConfig) 
		{
			DungeonPortalGenerator.addPieces(chunkGenerator, structureManager, new BlockPos(x << 4, 0, z << 4), children, random);
            this.setBoundingBoxFromChildren();
		}
		
		static
		{
			StructurePoolBasedGenerator.REGISTRY.add(
	                new StructurePool(
	                        BASE_POOL,
	                        new Identifier("empty"),
	                        ImmutableList.of(
	                                Pair.of(new SinglePoolElement("lsc:dungeons/portals/overworld_portal_1"), 1)
	                        ),
	                        StructurePool.Projection.RIGID
	                )
	        );
		}
	}
}
