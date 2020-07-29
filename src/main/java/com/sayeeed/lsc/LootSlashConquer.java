package com.sayeeed.lsc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sayeeed.lsc.init.LSCBlockEntities;
import com.sayeeed.lsc.init.LSCBlocks;
import com.sayeeed.lsc.init.LSCDimensions;
import com.sayeeed.lsc.init.LSCEvents;
import com.sayeeed.lsc.init.LSCItems;
import com.sayeeed.lsc.init.LSCStructures;
import com.sayeeed.lsc.util.Reference;

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
	public static final Logger LOGGER = LogManager.getLogger(Reference.MODID);
	
	/**
	 * initial call to begin mod loading. called before an assets etc. have been loaded.
	 */
	@Override
	public void onInitialize() 
	{
		LSCItems.registerItems();
		LSCBlocks.registerBlocks();
		LSCBlockEntities.registerBlockEntities();
		LSCEvents.registerEvents();
		LSCStructures.registerStructures();
		LSCDimensions.registerDimensions();
	}
}
