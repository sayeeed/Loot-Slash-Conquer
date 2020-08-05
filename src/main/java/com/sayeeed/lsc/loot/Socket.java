package com.sayeeed.lsc.loot;

import java.util.ArrayList;

import com.google.common.collect.Lists;
import com.sayeeed.lsc.loot.socket.weapon.FireDamageSocket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeeed
 *
 */
public abstract class Socket 
{
	public static final Socket FIRE_DAMAGE = new FireDamageSocket("Fire Damage");
	
	public static ArrayList<Socket> ALL_SOCKETS = Lists.newArrayList();
	
	private String name;
	
	public Socket(String name)
	{
		this.name = name;
	}
	
	public boolean hasSocket(CompoundTag tag)
	{
		return tag.getBoolean(name);
	}
	
	public void addSocket(ItemStack stack, CompoundTag tag, Rarity rarity)
	{
		tag.putBoolean(name, true);
		tag.putInt(name + "_rarity", rarity.ordinal());
		
		// testing
		tag.putDouble(name + "_value", 3.0);
	}
	
	public void removeSocket(CompoundTag tag)
	{
		tag.remove(name);
		tag.remove(name + "_rarity");
		tag.remove(name + "_value");
	}
	
	public double getSocketValue(CompoundTag tag)
	{
		return tag.getDouble(name + "_value");
	}
	
	public Rarity getSocketRarity(CompoundTag tag)
	{
		switch (tag.getInt(name + "_rarity"))
		{
			case 1:
				return Rarity.COMMON;
			case 2:
				return Rarity.UNCOMMON;
			case 3:
				return Rarity.RARE;
			case 4:
				return Rarity.EPIC;
			case 5:
				return Rarity.LEGENDARY;
			default:
				return Rarity.DEFAULT;
		}
	}
	
	@Environment(EnvType.CLIENT)
	public abstract String getTooltipDisplay(CompoundTag tag);
	
	public String getName()
	{
		return name;
	}
	
	static
	{
		// weapon sockets
		ALL_SOCKETS.add(FIRE_DAMAGE);
	}
}
