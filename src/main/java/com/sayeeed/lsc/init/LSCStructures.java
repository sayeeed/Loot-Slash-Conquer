package com.sayeeed.lsc.init;

import com.sayeeed.lsc.util.Reference;
import com.sayeeed.lsc.worldgen.generator.DungeonPortalGenerator;
import com.sayeeed.lsc.worldgen.structure.DungeonPortalStructure;

import net.earthcomputer.libstructure.LibStructure;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/**
 * 
 * @author sayeeed
 *
 */
public class LSCStructures 
{
	public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> DUNGEON_PORTAL = register("dungeon_portal_feature", new DungeonPortalStructure(), 165755306, false, false);
    public static final StructurePieceType DUNGEON_PORTAL_PIECE = Registry.register(Registry.STRUCTURE_PIECE, Reference.id("dungeon_portal_piece"), DungeonPortalGenerator.Piece::new);

    public static Biome.Category category;
    
    /**
     * Helper method to register structures using @LibStructure
     * @param name
     * @param structure
     * @param salt
     * @param surfaceAdjusting
     * @param singleBiome
     * @return
     */
	private static ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> register(String name, StructureFeature<DefaultFeatureConfig> structure, int salt, boolean surfaceAdjusting, boolean singleBiome) 
	{
        ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> configuredStructure = structure.configure(FeatureConfig.DEFAULT);
        if (surfaceAdjusting) {
            LibStructure.registerSurfaceAdjustingStructure(Reference.id(name), structure, GenerationStep.Feature.UNDERGROUND_DECORATION, singleBiome? new StructureConfig(32, 8, salt) : new StructureConfig(40, 16, salt), structure.configure(FeatureConfig.DEFAULT));

        } else {
            LibStructure.registerStructure(Reference.id(name), structure, GenerationStep.Feature.UNDERGROUND_DECORATION, new StructureConfig(32, 16, salt), structure.configure(FeatureConfig.DEFAULT));
        }
        return configuredStructure;
    }
	
	public static void registerStructures()
	{
		Registry.BIOME.forEach(LSCStructures::putStructures);
		RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> putStructures(biome));
	}
	
	/**
	 * Adds our structures to specific biomes.
	 * @param biome
	 */
	public static void putStructures(Biome biome)
	{
		category = biome.getCategory();
		
		if (category == Biome.Category.PLAINS) // TODO: change biome spawns
		{
			biome.addStructureFeature(DUNGEON_PORTAL);
		}
	}
}
