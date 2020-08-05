package com.sayeeed.lsc.client.event;

import java.util.List;

import com.sayeeed.lsc.loot.Rarity;
import com.sayeeed.lsc.loot.Socket;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/**
 * 
 * @author sayeeed
 *
 */
@Environment(EnvType.CLIENT)
public class ItemTooltipEvent 
{
	public static void registerCallback()
	{
		ItemTooltipCallback.EVENT.register((stack, context, lines) -> {
			onItemTooltip(stack, context, lines);
		});
	}
	
	private static void onItemTooltip(ItemStack stack, TooltipContext context, List<Text> lines)
	{
		if (stack.getItem() instanceof SwordItem)
		{	
			CompoundTag tag = stack.getTag();
			
			appendRarity(stack, tag, lines);
			appendStats(stack, lines);
			appendSockets(stack, tag, lines);
		}
	}
	
	private static void appendRarity(ItemStack stack, CompoundTag tag, List<Text> lines)
	{
		Rarity rarity = Rarity.getRarity(tag);
		lines.add(1, new LiteralText(rarity.getColor() + rarity.getName()));
		stack.setCustomName(new LiteralText(rarity.getColor() + stack.getName().getString()));
	}
	
	private static void appendStats(ItemStack stack, List<Text> lines)
	{
		
	}
	
	private static void appendSockets(ItemStack stack, CompoundTag tag, List<Text> lines)
	{
		// temp
		tag.putInt("socket_slots", Rarity.getRarity(tag).ordinal());
		
		int sockets = 0;
		
		lines.add(new LiteralText("Sockets"));
		
		for (Socket socket : Socket.ALL_SOCKETS)
		{
			if (socket.hasSocket(tag))
			{
				sockets++;
				lines.add(new LiteralText(socket.getSocketRarity(tag).getColor() + " * " + socket.getName()));
			}
		}
		
		for (int i = sockets; i < tag.getInt("socket_slots"); i++)
		{
			lines.add(new LiteralText(Formatting.DARK_GRAY + " * Empty Socket"));
		}
	}
}
