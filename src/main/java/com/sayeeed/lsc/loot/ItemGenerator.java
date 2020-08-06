package com.sayeeed.lsc.loot;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundTag;

/**
 * 
 * @author sayeeed
 *
 * Handles the generation of custom item stats.
 * 
 */
public class ItemGenerator 
{
	public static void createItem(ItemStack stack)
	{
		CompoundTag tag = stack.getTag();
		
		if (stack.getItem() instanceof SwordItem)
		{
			
		}
	}
}
