package com.sayeeed.lsc.loot;

import java.util.Random;

import com.sayeeed.lsc.util.RandomCollection;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Formatting;

/**
 * 
 * @author sayeeed
 * 
 * helper class for managing nbt data for item rarities
 * 
 * TODO: add translatable names
 *
 */
public enum Rarity 
{
	DEFAULT("default", Formatting.DARK_GRAY, 0.0),
	COMMON("Common", Formatting.WHITE, 0.65),
	UNCOMMON("Uncommon", Formatting.DARK_GREEN, 0.17),
	RARE("Rare", Formatting.AQUA, 0.1),
	EPIC("Epic", Formatting.DARK_PURPLE, 0.05),
	LEGENDARY("Legendary", Formatting.GOLD, 0.025),
	EXOTIC("Exotic", Formatting.LIGHT_PURPLE, 0.005);
	
	private String name;
	private String color;
	private double chance;
	
	private static final RandomCollection<Rarity> RANDOM_RARITIES = new RandomCollection<Rarity>();
	
	Rarity(String name, Object color, double chance)
	{
		this.name = name;
		this.color = color.toString();
		this.chance = chance;
	}
	
	/**
	 * Returns a randomized rarity.
	 * @param nbt
	 * @param rand
	 * @return
	 */
	public static Rarity getRandomRarity(CompoundTag nbt, Random rand)
	{	
		return RANDOM_RARITIES.next(rand);
	}
	
	/**
	 * Return the current rarity in the given NBTTagCompound. Returns Default if it can't find it.
	 * @param nbt
	 * @return
	 */
	public static Rarity getRarity(CompoundTag nbt)
	{
		return nbt.contains(Reference.RARITY_TAG) ? Rarity.values()[nbt.getInt(Reference.RARITY_TAG)] : DEFAULT;
	}
	
	/**
	 * Sets the rarity specified to the given NBTTagCompound.
	 * @param nbt
	 * @param rarity
	 */
	public static void setRarity(CompoundTag nbt, Rarity rarity)
	{
		nbt.putInt(Reference.RARITY_TAG, rarity.ordinal());
	}

	
	
	/*
	 * getters and setters
	 */
	
	public String getName() 
	{
		return name;
	}

	public String getColor() 
	{
		return color;
	}

	public double getChance() 
	{
		return chance;
	}
	
	/**
	 * iterate through all rarities and add them to this collection.
	 */
	static
	{
		for (Rarity rarity : Rarity.values())
		{
			if (rarity.getChance() > 0.0)
			{
				RANDOM_RARITIES.add(rarity.getChance(), rarity);
			}
		}
	}
}
