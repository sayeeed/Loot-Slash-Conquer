package com.sayeeed.lsc.init;

import com.sayeeed.lsc.item.ItemGem;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * 
 * @author sayeeed
 * 
 * class to contain items.
 *
 */
public class LSCItems 
{
	public static final Item GARNET_GEM = new ItemGem(new Item.Settings());
	public static final Item JADE_GEM = new ItemGem(new Item.Settings());
	public static final Item SAPPHIRE_GEM = new ItemGem(new Item.Settings());
	public static final Item AMETHYST_GEM = new ItemGem(new Item.Settings());
	public static final Item FLAWLESS_DIAMOND_GEM = new ItemGem(new Item.Settings());
	
	public static void registerItems()
	{
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "garnet_gem"), GARNET_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "jade_gem"), JADE_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "sapphire_gem"), SAPPHIRE_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "amethyst_gem"), AMETHYST_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "flawless_diamond_gem"), FLAWLESS_DIAMOND_GEM);
	}
}
