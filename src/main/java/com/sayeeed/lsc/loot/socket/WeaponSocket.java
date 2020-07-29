package com.sayeeed.lsc.loot.socket;

import com.sayeeed.lsc.loot.Socket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author sayeeed
 *
 */
public abstract class WeaponSocket extends Socket
{
	public WeaponSocket(String name) 
	{
		super(name);
	}
	
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy) { }
}
