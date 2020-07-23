package com.sayeeed.lsc;

import com.sayeeed.lsc.init.LSCBlocks;
import com.sayeeed.lsc.init.LSCDimensions;
import com.sayeeed.lsc.init.LSCItems;
import com.sayeeed.lsc.init.LSCStructures;

import net.fabricmc.api.ModInitializer;

/**
 * 
 * @author sayeeed
 * 
 * a minecraft mod about exploring and loot.
 *
 */
public class LootSlashConquer implements ModInitializer
{
	/**
	 * initial call to begin mod loading. called before an assets etc. have been loaded.
	 */
	@Override
	public void onInitialize() 
	{
		LSCItems.registerItems();
		LSCBlocks.registerBlocks();
		LSCStructures.registerStructures();
		LSCDimensions.registerDimensions();
	}
}
