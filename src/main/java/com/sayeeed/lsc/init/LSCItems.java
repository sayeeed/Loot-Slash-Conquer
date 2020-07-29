package com.sayeeed.lsc.init;

import com.sayeeed.lsc.item.GemItem;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * 
 * @author sayeeed
 *
 */
public class LSCItems 
{
	// normal items
	public static final Item GARNET_GEM = new GemItem(new Item.Settings());
	public static final Item JADE_GEM = new GemItem(new Item.Settings());
	public static final Item SAPPHIRE_GEM = new GemItem(new Item.Settings());
	public static final Item AMETHYST_GEM = new GemItem(new Item.Settings());
	public static final Item FLAWLESS_DIAMOND_GEM = new GemItem(new Item.Settings());
	
	// weapons
	public static final Item WOODEN_DAGGER = new SwordItem(ToolMaterials.WOOD, 1, -1.6F, new Item.Settings());
	public static final Item WOODEN_MACE = new SwordItem(ToolMaterials.WOOD, 5, -3.0F, new Item.Settings());
	public static final Item STONE_DAGGER = new SwordItem(ToolMaterials.STONE, 1, -1.6F, new Item.Settings());
	public static final Item STONE_MACE = new SwordItem(ToolMaterials.STONE, 5, -3.0F, new Item.Settings());
	public static final Item GOLDEN_DAGGER = new SwordItem(ToolMaterials.GOLD, 1, -1.6F, new Item.Settings());
	public static final Item GOLDEN_MACE = new SwordItem(ToolMaterials.GOLD, 5, -3.0F, new Item.Settings());
	public static final Item IRON_DAGGER = new SwordItem(ToolMaterials.IRON, 1, -1.6F, new Item.Settings());
	public static final Item IRON_MACE = new SwordItem(ToolMaterials.IRON, 5, -3.0F, new Item.Settings());
	public static final Item DIAMOND_DAGGER = new SwordItem(ToolMaterials.DIAMOND, 1, -1.6F, new Item.Settings());
	public static final Item DIAMOND_MACE = new SwordItem(ToolMaterials.DIAMOND, 5, -3.0F, new Item.Settings());
	public static final Item NETHERITE_DAGGER = new SwordItem(ToolMaterials.NETHERITE, 1, -1.6F, new Item.Settings());
	public static final Item NETHERITE_MACE = new SwordItem(ToolMaterials.NETHERITE, 5, -3.0F, new Item.Settings());
	
	public static void registerItems()
	{
		// normal items
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "garnet_gem"), GARNET_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "jade_gem"), JADE_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "sapphire_gem"), SAPPHIRE_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "amethyst_gem"), AMETHYST_GEM);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "flawless_diamond_gem"), FLAWLESS_DIAMOND_GEM);
		
		// weapons
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "wooden_dagger"), WOODEN_DAGGER);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "wooden_mace"), WOODEN_MACE);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "stone_dagger"), STONE_DAGGER);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "stone_mace"), STONE_MACE);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "golden_dagger"), GOLDEN_DAGGER);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "golden_mace"), GOLDEN_MACE);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "iron_dagger"), IRON_DAGGER);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "iron_mace"), IRON_MACE);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "diamond_dagger"), DIAMOND_DAGGER);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "diamond_mace"), DIAMOND_MACE);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "netherite_dagger"), NETHERITE_DAGGER);
		Registry.register(Registry.ITEM, new Identifier(Reference.MODID, "netherite_mace"), NETHERITE_MACE);
	}
}
