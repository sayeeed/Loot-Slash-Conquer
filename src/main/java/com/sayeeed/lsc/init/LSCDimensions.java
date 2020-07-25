package com.sayeeed.lsc.init;

import com.sayeeed.lsc.worldgen.dimension.DungeonChunkGenerator;

import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

/**
 * 
 * @author sayeeed
 *
 * 
 *
 */
@SuppressWarnings("deprecation")
public class LSCDimensions 
{
	public static final RegistryKey<World> DUNGEON_DIMENSION = RegistryKey.of(Registry.DIMENSION, new Identifier("lsc", "dungeon_dimension"));;

	public static void registerDimensions()
	{
		Registry.register(Registry.CHUNK_GENERATOR, new Identifier("lsc", "dungeon_dimension"), DungeonChunkGenerator.CODEC);

		FabricDimensions.registerDefaultPlacer(DUNGEON_DIMENSION, LSCDimensions::placeEntityInVoid);
	}
	
	@SuppressWarnings("unused")
	private static BlockPattern.TeleportTarget placeEntity(Entity teleported, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) 
	{
		return new BlockPattern.TeleportTarget(new Vec3d(0, 100, 0), Vec3d.ZERO, 0);
	}

	private static BlockPattern.TeleportTarget placeEntityInVoid(Entity teleported, ServerWorld destination, Direction portalDir, double horizontalOffset, double verticalOffset) 
	{
		destination.setBlockState(new BlockPos(0, 100, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
		destination.setBlockState(new BlockPos(0, 101, 0), Blocks.TORCH.getDefaultState());
		return new BlockPattern.TeleportTarget(new Vec3d(0.5, 101, 0.5), Vec3d.ZERO, 0);
	}
}
