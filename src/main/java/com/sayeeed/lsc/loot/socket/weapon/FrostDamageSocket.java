package com.sayeeed.lsc.loot.socket.weapon;

import com.sayeeed.lsc.damage.LSCDamageSource;
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
public class FrostDamageSocket extends Socket implements WeaponSocket
{
	public FrostDamageSocket(String name)
	{
		super(name);
	}
	
	@Override
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy)
	{
		enemy.damage(LSCDamageSource.SHOCK, (float) this.getSocketValue(stack.getTag()));
	}

	@Override
	public String getTooltipDisplay(CompoundTag tag) 
	{
		String tooltip = " * +" + getSocketValue(tag) + " Frost Damage";
		
		return getSocketRarity(tag).getColor() + tooltip;
	}
}
