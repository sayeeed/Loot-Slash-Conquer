package com.sayeeed.lsc.loot.socket.weapon;

import com.sayeeed.lsc.loot.Socket;
import com.sayeeed.lsc.loot.socket.WeaponSocket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeeed
 *
 */
public class LifeStealSocket extends Socket implements WeaponSocket
{
	public LifeStealSocket(String name) 
	{
		super(name);
	}

	@Override
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy) 
	{
		double lifeStealPercentage = this.getSocketValue(stack.getTag());
		attacker.heal((float) (lifeStealPercentage * damage));
	}

	@Override
	public String getTooltipDisplay(CompoundTag tag) 
	{
		String tooltip = " * +" + getSocketValue(tag) + " Life Steal";
		
		return getSocketRarity(tag).getColor() + tooltip;
	}	
}
