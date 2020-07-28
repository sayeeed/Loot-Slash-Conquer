package com.sayeeed.lsc.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.datafixers.util.Either;
import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.dungeon.DungeonGenerator;
import com.sayeeed.lsc.init.LSCDimensions;

import net.minecraft.block.Blocks;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.structure.Structure;
import net.minecraft.structure.Structure.StructureBlockInfo;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

/**
 * 
 * @author sayeeed
 *
 */
@Mixin(SinglePoolElement.class)
public abstract class SinglePoolElementMixin
{
	@Shadow @Final protected Either<Identifier, Structure> field_24015;
	
	@Inject(at = @At(value = "RETURN", ordinal = 1), method = "generate")
	private void generate(StructureManager structureManager, ServerWorldAccess serverWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockPos blockPos2, BlockRotation blockRotation, BlockBox blockBox, Random random, boolean keepJigsaws, CallbackInfoReturnable<Boolean> callbackInfo)
	{
		LootSlashConquer.LOGGER.info("mixing running...");
		
		if (serverWorldAccess.getWorld().getRegistryKey() == LSCDimensions.DUNGEON_DIMENSION)
		{
			Structure structure = this.method_27233(structureManager);
			StructurePlacementData structurePlacementData = this.shadow$createPlacementData(blockRotation, blockBox, keepJigsaws);
			List<StructureBlockInfo> structureBlockInfos = structure.getInfosForBlock(blockPos, structurePlacementData, Blocks.STRUCTURE_BLOCK);
			Iterator<StructureBlockInfo> iterator = structureBlockInfos.iterator();
			
			while (iterator.hasNext())
			{
				StructureBlockInfo info = iterator.next();
				StructureBlockMode mode = StructureBlockMode.valueOf(info.tag.getString("mode"));
				
				if (mode == StructureBlockMode.DATA)
				{
					DungeonGenerator.handleDataBlocks(serverWorldAccess.getWorld(), info);
				}
			}
		}
	}
	
	@Shadow(prefix = "shadow$") protected abstract StructurePlacementData shadow$createPlacementData(BlockRotation blockRotation, BlockBox blockBox, boolean keepJigsaws);
	
	/**
	 * Pulled from SinglePoolElement
	 * @param structureManager
	 * @return
	 */
	private Structure method_27233(StructureManager structureManager) 
	{
		return (Structure)this.field_24015.map(structureManager::getStructureOrBlank, Function.identity());
	}
}
