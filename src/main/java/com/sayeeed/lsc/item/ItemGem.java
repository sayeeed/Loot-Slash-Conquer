package com.sayeeed.lsc.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * 
 * @author sayeeed
 *
 */
public class ItemGem extends Item
{
	public ItemGem(Settings settings) 
	{
		super(settings);
		settings.group(ItemGroup.MISC); // TODO: change to custom item group
	}
	
	// TODO: customize tooltip?
}
