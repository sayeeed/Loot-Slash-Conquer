package com.sayeeed.lsc.init;

import com.sayeeed.lsc.item.EngramItem;
import com.sayeeed.lsc.item.GemItem;
import com.sayeeed.lsc.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
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
	public static final Item COMMON_ENGRAM = new EngramItem(new Item.Settings());
	public static final Item UNCOMMON_ENGRAM = new EngramItem(new Item.Settings());
	public static final Item RARE_ENGRAM = new EngramItem(new Item.Settings());
	public static final Item EPIC_ENGRAM = new EngramItem(new Item.Settings());
	public static final Item LEGENDARY_ENGRAM = new EngramItem(new Item.Settings());
	public static final Item EXOTIC_ENGRAM = new EngramItem(new Item.Settings());
	public static final Item SPECIAL_ENGRAM = new EngramItem(new Item.Settings());
	
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
		Registry.register(Registry.ITEM, Reference.id("garnet_gem"), GARNET_GEM);
		Registry.register(Registry.ITEM, Reference.id("jade_gem"), JADE_GEM);
		Registry.register(Registry.ITEM, Reference.id("sapphire_gem"), SAPPHIRE_GEM);
		Registry.register(Registry.ITEM, Reference.id("amethyst_gem"), AMETHYST_GEM);
		Registry.register(Registry.ITEM, Reference.id("flawless_diamond_gem"), FLAWLESS_DIAMOND_GEM);
		Registry.register(Registry.ITEM, Reference.id("common_engram"), COMMON_ENGRAM);
		Registry.register(Registry.ITEM, Reference.id("uncommon_engram"), UNCOMMON_ENGRAM);
		Registry.register(Registry.ITEM, Reference.id("rare_engram"), RARE_ENGRAM);
		Registry.register(Registry.ITEM, Reference.id("epic_engram"), EPIC_ENGRAM);
		Registry.register(Registry.ITEM, Reference.id("legendary_engram"), LEGENDARY_ENGRAM);
		Registry.register(Registry.ITEM, Reference.id("exotic_engram"), EXOTIC_ENGRAM);
		
		// weapons
		Registry.register(Registry.ITEM, Reference.id("wooden_dagger"), WOODEN_DAGGER);
		Registry.register(Registry.ITEM, Reference.id("wooden_mace"), WOODEN_MACE);
		Registry.register(Registry.ITEM, Reference.id("stone_dagger"), STONE_DAGGER);
		Registry.register(Registry.ITEM, Reference.id("stone_mace"), STONE_MACE);
		Registry.register(Registry.ITEM, Reference.id("golden_dagger"), GOLDEN_DAGGER);
		Registry.register(Registry.ITEM, Reference.id("golden_mace"), GOLDEN_MACE);
		Registry.register(Registry.ITEM, Reference.id("iron_dagger"), IRON_DAGGER);
		Registry.register(Registry.ITEM, Reference.id("iron_mace"), IRON_MACE);
		Registry.register(Registry.ITEM, Reference.id("diamond_dagger"), DIAMOND_DAGGER);
		Registry.register(Registry.ITEM, Reference.id("diamond_mace"), DIAMOND_MACE);
		Registry.register(Registry.ITEM, Reference.id("netherite_dagger"), NETHERITE_DAGGER);
		Registry.register(Registry.ITEM, Reference.id("netherite_mace"), NETHERITE_MACE);
	}
}
