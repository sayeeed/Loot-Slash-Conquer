package com.sayeeed.lsc.loot.socket.weapon;

import com.sayeeed.lsc.loot.Socket;
import com.sayeeed.lsc.loot.socket.WeaponSocket;

import com.sayeeed.lsc.damage.LSCDamageSource;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeeed
 *
 */
public class ShockDamageSocket extends Socket implements WeaponSocket
{
	public ShockDamageSocket(String name)
	{
		super(name);
	}
	
	@Override
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy)
	{
		enemy.damage(LSCDamageSource.FROST, (float) this.getSocketValue(stack.getTag()));
	}

	@Override
	public String getTooltipDisplay(CompoundTag tag) 
	{
		String tooltip = " * +" + getSocketValue(tag) + " Shock Damage";
		
		return getSocketRarity(tag).getColor() + tooltip;
	}
}
