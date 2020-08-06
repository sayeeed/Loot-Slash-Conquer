package com.sayeeed.lsc.loot.socket.weapon;

import com.sayeeed.lsc.LootSlashConquer;
import com.sayeeed.lsc.damage.LSCDamageSource;
import com.sayeeed.lsc.loot.Socket;
import com.sayeeed.lsc.loot.socket.WeaponSocket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeed
 *
 * Inflicts additional fire elemental damage. This damage bypasses armor as well as typical Fire Resistance.
 *
 */
public class FireDamageSocket extends Socket implements WeaponSocket
{
	public FireDamageSocket(String name)
	{
		super(name);
	}
	
	@Override
	public void onHit(ItemStack stack, float damage, LivingEntity attacker, LivingEntity enemy)
	{
		LootSlashConquer.LOGGER.info("adding fire damage...");
		enemy.damage(LSCDamageSource.FIRE, (float) this.getSocketValue(stack.getTag()));
	}

	@Override
	public String getTooltipDisplay(CompoundTag tag) 
	{
		String tooltip = " * +" + getSocketValue(tag) + " Fire Damage";
		
		return getSocketRarity(tag).getColor() + tooltip;
	}
}
