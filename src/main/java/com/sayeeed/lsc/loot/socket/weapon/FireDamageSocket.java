package com.sayeeed.lsc.loot.socket.weapon;

import com.sayeeed.lsc.loot.Socket;

import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeed
 *
 */
public class FireDamageSocket extends Socket
{
	public FireDamageSocket(String name) 
	{
		super(name);
	}

	@Override
	public String getTooltipDisplay(CompoundTag nbt) {
		return null;
	}
}
