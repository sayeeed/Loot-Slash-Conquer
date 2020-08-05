package com.sayeeed.lsc.loot.socket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author sayeeed
 *
 */
public interface WeaponSocket
{
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy);
}
