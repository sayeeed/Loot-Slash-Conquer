package com.sayeeed.lsc.event;

import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.loot.Rarity;
import com.sayeeed.lsc.loot.Socket;
import com.sayeeed.lsc.loot.socket.WeaponSocket;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

/**
 * 
 * @author sayeeed
 *
 */
public class AttackEntityEvent 
{
	public static void registerCallback()
	{
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			onPlayerAttack(player, world, hand, entity, hitResult);
			return ActionResult.PASS;
		});
	}
	
	private static void onPlayerAttack(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult)
	{
		if (!world.isClient)
		{
			ItemStack mainHandStack = player.getMainHandStack();
			CompoundTag tag = mainHandStack.getTag();
			
			if (mainHandStack.getItem() instanceof SwordItem && entity instanceof LivingEntity)
			{
				if (Rarity.getRarity(tag) == Rarity.DEFAULT)
				{
					Rarity.setRarity(tag, Rarity.getRandomRarity(tag, world.random));
					Socket.FIRE_DAMAGE.addSocket(mainHandStack, tag, Rarity.getRandomRarity(tag, world.random));
				}
				
				double currentDamage = player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
				LootSlashConquer.LOGGER.info("Weapon damage: " + currentDamage);
				
				applySockets(player, mainHandStack, tag, currentDamage, (LivingEntity) entity);
			}
		}
	}
	
	private static void applySockets(PlayerEntity player, ItemStack stack, CompoundTag tag, double damage, LivingEntity enemy)
	{
		for (Socket socket : Socket.ALL_SOCKETS)
		{
			if (socket instanceof WeaponSocket)
			{
				((WeaponSocket) socket).onHit(stack, (float) damage, player, enemy);
			}
		}
	}
}
