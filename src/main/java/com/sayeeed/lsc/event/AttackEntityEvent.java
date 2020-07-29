package com.sayeeed.lsc.event;

import com.sayeeed.lsc.LootSlashConquer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
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
			
			if (mainHandStack.getItem() instanceof SwordItem)
			{
				double currentDamage = player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
				player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(3.0);
				currentDamage -= player.getAttributeBaseValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
				LootSlashConquer.LOGGER.info("Weapon damage: " + currentDamage);
			}
		}
	}
}
