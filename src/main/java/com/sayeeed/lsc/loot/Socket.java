package com.sayeeed.lsc.loot;

import com.sayeeed.lsc.init.LSCItems;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeeed
 *
 */
public abstract class Socket 
{
	
	
	private String name;
	
	public Socket(String name)
	{
		this.name = name;
	}
	
	public boolean hasSocket(CompoundTag tag)
	{
		return tag.getBoolean(name);
	}
	
	public void addSocket(ItemStack stack, CompoundTag tag)
	{
		tag.putBoolean(name, true);
		
		if (stack.getItem() == LSCItems.GARNET_GEM) tag.putInt(name + "_rarity", 1);
		else if (stack.getItem() == LSCItems.JADE_GEM) tag.putInt(name + "_rarity", 2);
		else if (stack.getItem() == LSCItems.SAPPHIRE_GEM) tag.putInt(name + "_rarity", 3);
		else if (stack.getItem() == LSCItems.AMETHYST_GEM) tag.putInt(name + "_rarity", 4);
		else if (stack.getItem() == LSCItems.FLAWLESS_DIAMOND_GEM) tag.putInt(name + "_rarity", 5);
	}
	
	public void removeSocket(CompoundTag tag)
	{
		tag.remove(name);
		tag.remove(name + "_rarity");
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
	
	// client-side annotation?
	public abstract String getTooltipDisplay(CompoundTag nbt);
	
	public String getName()
	{
		return name;
	}
}
