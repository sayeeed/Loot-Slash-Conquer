package com.sayeeed.lsc.util;

import net.minecraft.util.Identifier;

/**
 * 
 * @author sayeeed
 * 
 * stores frequently used mod-wide information
 *
 */
public class Reference 
{
	public static final String MODID = "lsc";
	
	public static final String RARITY_TAG = "LSC_RARITY";
	
	public static Identifier id(String name) 
	{
        return new Identifier(MODID, name);
    }
}
