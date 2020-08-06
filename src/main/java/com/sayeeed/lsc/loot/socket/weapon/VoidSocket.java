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
public class VoidSocket extends Socket implements WeaponSocket
{
	public VoidSocket(String name)
	{
		super(name);
	}
	
	@Override
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy)
	{
		if (Math.random() < this.getSocketValue(stack.getTag()))
		{
			enemy.setHealth(0.1F);
		}
	}

	@Override
	public String getTooltipDisplay(CompoundTag tag) 
	{
		String tooltip = " * +" + getSocketValue(tag) + " chance to instantly kill an enemy.";
		
		return getSocketRarity(tag).getColor() + tooltip;
	}
}
