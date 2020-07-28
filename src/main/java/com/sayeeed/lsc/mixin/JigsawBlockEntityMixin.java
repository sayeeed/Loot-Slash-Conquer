package com.sayeeed.lsc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.sayeeed.lsc.LootSlashConquer;

import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.JigsawBlockEntity;
import net.minecraft.server.world.ServerWorld;

@Mixin(JigsawBlockEntity.class)
public class JigsawBlockEntityMixin extends BlockEntity
{
	public JigsawBlockEntityMixin(BlockEntityType<?> type) 
	{
		super(type);
	}

	@Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/pool/StructurePoolBasedGenerator;method_27230(Lnet/minecraft/structure/PoolStructurePiece;ILnet/minecraft/structure/pool/StructurePoolBasedGenerator$PieceFactory;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;Ljava/util/List;Ljava/util/Random;)V"))
	private void generate(ServerWorld world, int maxDepth, boolean keepJigsaws, CallbackInfo info)
	{
		LootSlashConquer.LOGGER.info("Mixin running in ......");
		
		world.setBlockState(this.getPos(), Blocks.STONE_BRICKS.getDefaultState());
	}
}
