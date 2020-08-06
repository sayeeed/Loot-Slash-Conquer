package com.sayeeed.lsc.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * 
 * @author sayeeed
 *
 */
public class EngramItem extends Item
{
	public EngramItem(Settings settings) 
	{
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) 
	{
		ItemStack stack = player.getStackInHand(hand);
		
		
		
		return super.use(world, player, hand);
	}
}
