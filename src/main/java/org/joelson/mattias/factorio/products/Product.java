package org.joelson.mattias.factorio.products;

import java.util.Map;
import java.util.Set;

public enum Product {

    /* Resources */

    COAL("Coal", "coal", 50, Producer.DRILLS, 1.0f, 1, Map.of()),
    STONE("Stone", "stone", 50, Producer.DRILLS, 1.0f, 1, Map.of()),
    IRON_ORE("Iron ore", "iron-ore", 50, Producer.DRILLS, 1.0f, 1, Map.of()),
    COPPER_ORE("Copper ore", "copper-ore", 50, Producer.DRILLS, 1.0f, 1, Map.of()),
    CRUDE_OIL("Crude oil", "crude-oil", 100, Producer.PUMPJACKS, 1.0f, 2, Map.of()),
    WATER("Water", "water", 100, Producer.OFFSHORE_PUMPS, 1.0f, 1000, Map.of()),
    STEAM("Steam", "steam", 100, Producer.BOILERS, 1.0f, 60,
            Map.of(WATER, 60)),

    HEAVY_OIL("Heavy oil", "heavy-oil", 100, Producer.PETROLEUM_PRODUCERS, 0.0f, 0, Map.of()),
    LIGHT_OIL("Light oil", "light-oil", 100, Producer.PETROLEUM_PRODUCERS, 0.0f, 0, Map.of()),
    PETROLEUM_GAS("Petroleum gas", "petroleum-gas", 100, Producer.PETROLEUM_PRODUCERS, 0.0f, 0, Map.of()),
    LUBRICANT("Lubricant", "lubricant", 100, Producer.CHEMICAL_PLANTS, 1.0f, 10,
            Map.of(HEAVY_OIL, 10)),


    /* Materials */

    IRON_PLATE("Iron plate", "iron-plate", 100, Producer.FURNACES, 3.2f, 1,
            Map.of(IRON_ORE, 1)),
    COPPER_PLATE("Copper plate", "copper-plate", 100, Producer.FURNACES, 3.2f, 1,
            Map.of(COPPER_ORE, 1)),
    SOLID_FUEL("Solid fuel (light)", "solid-fuel", 50, Producer.CHEMICAL_PLANTS, 2.0f, 1,
            Map.of(LIGHT_OIL, 10)),
    STEEL_PLATE("Steel plate", "steel-plate", 100, Producer.FURNACES, 16.0f, 1,
            Map.of(IRON_PLATE, 5)),
    STONE_BRICK("Stone brick", "stone-brick", 100, Producer.FURNACES, 3.2f, 1,
            Map.of(STONE, 2)),
    PLASTIC_BAR("Plastic bar", "plastic-bar", 100, Producer.CHEMICAL_PLANTS, 1.0f, 2,
            Map.of(COAL, 1, PETROLEUM_GAS, 20)),
    SULFUR("Sulfur", "sulfur", 50, Producer.CHEMICAL_PLANTS, 1.0f, 2,
            Map.of(PETROLEUM_GAS, 30, WATER, 30)),
    SULFURIC_ACID("Sulfuric acid", "sulfuric-acid", 100, Producer.CHEMICAL_PLANTS, 1.0f, 50,
            Map.of(IRON_PLATE, 1, SULFUR, 5, WATER, 100)),
    BATTERY("Battery", "battery", 200, Producer.CHEMICAL_PLANTS, 4.0f, 1,
            Map.of(COPPER_PLATE, 1, IRON_PLATE, 1, SULFURIC_ACID, 20)),

    /* pre-Logistics */
    PIPE("Pipe", "pipe", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(IRON_PLATE, 1)),

    /* Crafting components */

    COPPER_CABLE("Copper cable", "copper-cable", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 2,
            Map.of(COPPER_PLATE, 1)),
    IRON_STICK("Iron stick", "iron-stick", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 2,
            Map.of(IRON_PLATE, 1)),
    IRON_GEAR("Iron gear wheel", "iron-gear-wheel", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(IRON_PLATE, 2)),
    ELECTRONIC_CIRCUIT("Electronic circuit", "electronic-circuit", 200, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(COPPER_CABLE, 3, IRON_PLATE, 1)),
    ADVANCED_CIRCUIT("Advanced circuit", "advanced-circuit", 200, Producer.ASSEMBLING_MACHINES, 6.0f, 1,
            Map.of(COPPER_CABLE, 4, ELECTRONIC_CIRCUIT, 2, PLASTIC_BAR, 2)),
    PROCESSING_UNIT("Processing unit", "processing-unit", 100, Producer.FLUID_ASSEMBLING_MACHINES, 10.0f, 1,
            Map.of(ADVANCED_CIRCUIT, 2, ELECTRONIC_CIRCUIT, 20, SULFURIC_ACID, 5)),
    ENGINE("Engine unit", "engine-unit", 50, Producer.ASSEMBLING_MACHINES, 10.0f, 1,
            Map.of(IRON_GEAR, 1, PIPE, 2, STEEL_PLATE, 1)),
    ELECTRIC_ENGINE("Electric engine unit", "electric-engine-unit", 50, Producer.FLUID_ASSEMBLING_MACHINES, 10.0f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 2, ENGINE, 1, LUBRICANT, 15)),
    FLYING_ROBOT_FRAME("Flying robot frame", "flying-robot-frame", 50, Producer.ASSEMBLING_MACHINES, 20, 1,
            Map.of(BATTERY, 2, ELECTRIC_ENGINE, 1, ELECTRONIC_CIRCUIT, 3, STEEL_PLATE, 1)),
    LOW_DENSITY_STRUCTURE("Low density structure", "low-density-structure", 10, Producer.ASSEMBLING_MACHINES, 20.0f, 1,
            Map.of(COPPER_PLATE, 20, PLASTIC_BAR, 5, STEEL_PLATE, 2)),

    /* Logistics */

    TRANSPORT_BELT("Transport belt", "transport-belt", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 2,
            Map.of(IRON_GEAR, 1, IRON_PLATE, 1)),

    INSERTER("Inserter", "inserter", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 1, IRON_GEAR, 1, IRON_PLATE, 1)),

    RAIL("Rail", "rail", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 2,
            Map.of(IRON_STICK, 1, STEEL_PLATE, 1, STONE, 1)),

    CONCRETE("Concrete", "concrete", 100, Producer.FLUID_ASSEMBLING_MACHINES, 10.0f, 10,
            Map.of(IRON_ORE, 1, STONE_BRICK, 5, WATER, 100)),

    /* Modules */

    SPEED_MODULE("Speed module", "speed-module", 50, Producer.ASSEMBLING_MACHINES, 15.0f, 1,
            Map.of(ADVANCED_CIRCUIT, 5, ELECTRONIC_CIRCUIT, 5)),
    PRODUCTIVITY_MODULE("Productivity module", "productivity-module", 50, Producer.ASSEMBLING_MACHINES, 15.0f, 1,
            Map.of(ADVANCED_CIRCUIT, 5, ELECTRONIC_CIRCUIT, 5)),

    /* Production */

    ELECTRIC_MINING_DRILL("Electric mining drill", "electric-mining-drill", 50, Producer.ASSEMBLING_MACHINES, 2.0f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 3, IRON_GEAR, 5, IRON_PLATE, 10)),
    PUMPJACK("Pumpjack", "pumpjack", 20, Producer.ASSEMBLING_MACHINES, 5.0f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 5, IRON_GEAR, 10, PIPE, 10, STEEL_PLATE, 5)),
    OFFSHORE_PUMP("Offshore pump", "offshore-pump", 20, Producer.ASSEMBLING_MACHINES, 1.0f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 2, IRON_GEAR, 1, PIPE, 1)),
    STONE_FURNACE("Stone furnace", "stone-furnace", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(STONE, 5)),
    ELECTRIC_FURNACE("Electric furnace", "electric-furnace", 50, Producer.ASSEMBLING_MACHINES, 5.0f, 1,
            Map.of(ADVANCED_CIRCUIT, 5, STEEL_PLATE, 10, STONE_BRICK, 10)),
    ASSEMBLING_MACHINE_1("Assembling machine 1", "assembling-machine-1", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 3, IRON_GEAR, 5, IRON_PLATE, 9)),
    ASSEMBLING_MACHINE_2("Assembling machine 2", "assembling-machine-2", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(ASSEMBLING_MACHINE_1, 1, ELECTRONIC_CIRCUIT, 3, IRON_GEAR, 5, STEEL_PLATE, 2)),
    ASSEMBLING_MACHINE_3("Assembling machine 3", "assembling-machine-3", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(ASSEMBLING_MACHINE_2, 2, SPEED_MODULE, 1)),
    OIL_REFINERY("Oil refinery", "oil-refinery", 10, Producer.ASSEMBLING_MACHINES, 8.0f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 10, IRON_GEAR, 10, PIPE, 10, STEEL_PLATE, 15, STONE_BRICK, 10)),
    CHEMICAL_PLANT("Chemical plant", "chemical-plant", 10, Producer.ASSEMBLING_MACHINES, 5.0f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 5, IRON_GEAR, 5, PIPE, 5, STEEL_PLATE, 5)),

    BOILER("Boiler", "boiler", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(PIPE, 4, STONE_FURNACE, 1)),

    SOLAR_PANEL("Solar panel", "solar-panel", 50, Producer.ASSEMBLING_MACHINES, 10.0f, 1,
            Map.of(COPPER_PLATE, 5, ELECTRONIC_CIRCUIT, 15, STEEL_PLATE, 5)),
    ACCUMULATOR("Accumulator", "accumulator", 50, Producer.ASSEMBLING_MACHINES, 10.0f, 1,
            Map.of(BATTERY, 5, IRON_PLATE, 2)),

    /* Combat */

    FIRARM_MAGAIZE("Firearm magazine", "firearm-magazine", 200, Producer.ASSEMBLING_MACHINES, 1.0f, 1,
            Map.of(IRON_PLATE, 4)),
    PIERCING_ROUNDS_MAGAZINE("Piercing rounds magazine", "piercing-rounds-magazine", 200, Producer.ASSEMBLING_MACHINES, 3.0f, 1,
            Map.of(COPPER_PLATE, 5, FIRARM_MAGAIZE, 1, STEEL_PLATE, 1)),
    GRENADE("Grenade", "grenade", 100, Producer.ASSEMBLING_MACHINES, 8.0f, 1,
            Map.of(COAL, 10, IRON_PLATE, 5)),
    WALL("Wall", "wall", 100, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(STONE_BRICK, 5)),
    RADAR("Radar", "radar", 50, Producer.ASSEMBLING_MACHINES, 0.5f, 1,
            Map.of(ELECTRONIC_CIRCUIT, 5, IRON_GEAR, 5, IRON_PLATE, 10)),
    ROCKET_SILO("Rocket silo", "rocket-silo", 1, Producer.ASSEMBLING_MACHINES, 30.0f, 1,
            Map.of(CONCRETE, 1000, ELECTRIC_ENGINE, 200, PIPE, 100, PROCESSING_UNIT, 200, STEEL_PLATE, 1000)),

    /* Rocket */

    ROCKET_CONTROL_UNIT("Rocket control unit", "rocket-control-unit", 10, Producer.ASSEMBLING_MACHINES, 30.0f, 1,
            Map.of(PROCESSING_UNIT, 1, SPEED_MODULE, 1)),
    ROCKET_FUEL("Rocket fuel", "rocket-fuel", 10, Producer.FLUID_ASSEMBLING_MACHINES, 30.0f, 1,
            Map.of(LIGHT_OIL, 10, SOLID_FUEL, 10)),
    ROCKET_PART("Rocket part", "rocket-part", 5, Producer.ROCKET_SILOS, 3.0f, 1,
            Map.of(LOW_DENSITY_STRUCTURE, 10, ROCKET_CONTROL_UNIT, 10, ROCKET_FUEL, 10)),

    /* pre-Combat */

    SATELLITE("Satellite", "satellite", 1, Producer.ASSEMBLING_MACHINES, 5.0f, 1,
            Map.of(ACCUMULATOR, 100, LOW_DENSITY_STRUCTURE, 100, PROCESSING_UNIT, 100, RADAR, 5, ROCKET_FUEL, 50, SOLAR_PANEL, 100)),

    /* Science */

    AUTOMATION_SCIENCE_PACK("Automation sciene pack", "automation-science-pack", 200, Producer.ASSEMBLING_MACHINES, 5.0f, 1,
            Map.of(COPPER_PLATE, 1, IRON_GEAR, 1)),
    LOGISTIC_SCIENCE_PACK("Logistic science pack", "logistic-science-pack", 200, Producer.ASSEMBLING_MACHINES, 6.0f, 1,
            Map.of(INSERTER, 1, TRANSPORT_BELT, 1)),
    MILITARY_SCIENCE_PACK("Military science pack", "military-science-pack", 200, Producer.ASSEMBLING_MACHINES, 10.0f, 2,
            Map.of(GRENADE, 1, PIERCING_ROUNDS_MAGAZINE, 1, WALL, 2)),
    CHEMICAL_SCIENCE_PACK("Chemical science pack", "chemical-science-pack", 200, Producer.ASSEMBLING_MACHINES, 24.0f, 2,
            Map.of(ADVANCED_CIRCUIT, 3, ENGINE, 2, SULFUR, 1)),
    PRODUCTION_SCIENE_PACK("Production science pack", "production-science-pack", 200, Producer.ASSEMBLING_MACHINES, 21.0f, 3,
            Map.of(ELECTRIC_FURNACE, 1, PRODUCTIVITY_MODULE, 1, RAIL, 30)),
    UTILITY_SCIENCE_PACK("Utility science pack", "utility-science-pack", 200, Producer.ASSEMBLING_MACHINES, 21.0f, 3,
            Map.of(FLYING_ROBOT_FRAME, 1, LOW_DENSITY_STRUCTURE, 3, PROCESSING_UNIT, 2)),
    SPACE_SCIENCE_PACK("Space science pack", "space-science-pack", 2000, Producer.ROCKET_SILOS, 40.33f, 1000,
            Map.of(ROCKET_PART, 100, SATELLITE, 1));

    private final String name;
    private final String internalName;
    private final int stackSize;
    private final Producer[] producers;
    private final float craftingTime;
    private final int produced;
    private final Map<Product, Integer> ingredients;

    Product(String name, String internalName, int stackSize, Producer[] producers, float craftingTime, int produced, Map<Product, Integer> ingredients) {
        this.name = name;
        this.internalName = internalName;
        this.producers = producers;
        this.craftingTime = craftingTime;
        this.stackSize = stackSize;
        this.produced = produced;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getInternalName() {
        return internalName;
    }

    public int getStackSize() {
        return stackSize;
    }

    public Producer[] getProducers() {
        return producers;
    }

    public float getCraftingTime() {
        return craftingTime;
    }

    public int getProduced() {
        return produced;
    }

    public Map<Product, Integer> getIngredients() {
        return ingredients;
    }

    static {
        Producer.setProducts();
    }

    public static final Set<Product> FLUIDS = Set.of(CRUDE_OIL, WATER, STEAM, HEAVY_OIL, LIGHT_OIL, PETROLEUM_GAS, SULFURIC_ACID, LUBRICANT);

    public static final Set<Product> OIL_PROCESSING_FLUIDS = Set.of(HEAVY_OIL, LIGHT_OIL, PETROLEUM_GAS);
}
