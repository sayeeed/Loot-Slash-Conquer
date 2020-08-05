package com.sayeeed.lsc.damage;

import net.minecraft.entity.damage.DamageSource;

/**
 * 
 * @author Chip Soud
 *
 */
public class LSCDamageSource extends DamageSource
{
	public static final DamageSource FIRE = new LSCDamageSource("fire").setBypassesArmor();
	public static final DamageSource FROST = new LSCDamageSource("frost").setBypassesArmor();
	public static final DamageSource SHOCK = new LSCDamageSource("shock").setBypassesArmor();
	
	protected LSCDamageSource(String name) 
	{
		super(name);
	}
}
