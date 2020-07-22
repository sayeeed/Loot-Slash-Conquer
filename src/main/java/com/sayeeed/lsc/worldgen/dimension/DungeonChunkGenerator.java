package com.sayeeed.lsc.worldgen.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

/**
 * 
 * @author sayeeed
 * 
 * custom dimension generator specifically to generate dungeon plots.
 *
 */
public class DungeonChunkGenerator extends ChunkGenerator
{
	protected final boolean customBool;
	
	public static final Codec<DungeonChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
		instance.group(
			BiomeSource.field_24713.fieldOf("biome_source")
					.forGetter((generator) -> generator.biomeSource),
			Codec.BOOL.fieldOf("custom_bool")
					.forGetter((generator) -> generator.customBool)
		)
		.apply(instance, instance.stable(DungeonChunkGenerator::new))
	);
	
	public DungeonChunkGenerator(BiomeSource biomeSource, boolean customBool) 
	{
		super(biomeSource, new StructuresConfig(false));
		this.customBool = customBool;
	}

	@Override
	protected Codec<? extends ChunkGenerator> method_28506() 
	{
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) 
	{
		return this;
	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) 
	{
		// fills in a 256x256 border for dungeons to fill.
		int y = 100;		
		BlockPos corner = new BlockPos((chunk.getPos().x * 16), y, (chunk.getPos().z * 16));
		
		if (corner.getX() % 256 == 0 || corner.getZ() % 256 == 0)
		{
			chunk.setBlockState(corner, Blocks.WHITE_CONCRETE.getDefaultState(), false);
		}
	}

	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) 
	{
		
	}

	@Override
	public int getHeight(int x, int z, Type heightmapType) 
	{
		return 0;
	}

	@Override
	public BlockView getColumnSample(int x, int z) 
	{
		return new VerticalBlockSample(new BlockState[0]);
	}
}
