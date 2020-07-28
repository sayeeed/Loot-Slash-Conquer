package com.sayeeed.lsc.init;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.sayeeed.lsc.util.Reference;
import com.sayeeed.lsc.worldgen.generator.DungeonPortalGenerator;
import com.sayeeed.lsc.worldgen.structure.DungeonPortalStructure;

import net.earthcomputer.libstructure.LibStructure;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.Identifier;
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
	
	private static final Identifier INITIALIZERS = Reference.id("initializers");
	private static final Identifier START = Reference.id("starting_pool");
	private static final Identifier MAIN = Reference.id("main");
	private static final Identifier HALLWAYS = Reference.id("hallways");
	private static final Identifier TERMINATORS = Reference.id("terminators");
	
	static
	{
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        INITIALIZERS,
                        new Identifier("empty"),
                        ImmutableList.of(
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/dungeon_initializer"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
		
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        START,
                        new Identifier("empty"),
                        ImmutableList.of(
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/starting_rooms/starting_room_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
		
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        MAIN,
                        TERMINATORS,
                        ImmutableList.of(
                                Pair.of(new SinglePoolElement("lsc:dungeons/rooms/simple_room_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/rooms/simple_room_2"), 3),
                                Pair.of(new SinglePoolElement("lsc:dungeons/rooms/simple_room_3"), 2),
                                Pair.of(new SinglePoolElement("lsc:dungeons/rooms/maze_room_1"), 2),
                                Pair.of(new SinglePoolElement("lsc:dungeons/rooms/trap_room_1"), 2),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_straight_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_right_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_left_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_t_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_4way_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
		
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        HALLWAYS,
                        TERMINATORS,
                        ImmutableList.of(
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_straight_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_right_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_left_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_t_1"), 1),
                                Pair.of(new SinglePoolElement("lsc:dungeons/hallways/hallway_4way_1"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
		
		StructurePoolBasedGenerator.REGISTRY.add(
                new StructurePool(
                        TERMINATORS,
                        new Identifier("empty"),
                        ImmutableList.of(
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/terminators/terminator"), 5),
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/rooms/loot/loot_room_1"), 1),
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/rooms/loot/loot_room_2"), 1),
                        		 Pair.of(new SinglePoolElement("lsc:dungeons/rooms/loot/loot_room_3"), 1)
                        ),
                        StructurePool.Projection.RIGID
                )
        );
	}
}
