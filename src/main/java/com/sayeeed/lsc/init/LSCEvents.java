package com.sayeeed.lsc.init;

import com.sayeeed.lsc.client.event.ItemTooltipEvent;
import com.sayeeed.lsc.event.AttackEntityEvent;

/**
 * 
 * @author sayeeed
 *
 */
public class LSCEvents 
{
	public static void registerEvents()
	{
		AttackEntityEvent.registerCallback();
		ItemTooltipEvent.registerCallback();
	}
}
