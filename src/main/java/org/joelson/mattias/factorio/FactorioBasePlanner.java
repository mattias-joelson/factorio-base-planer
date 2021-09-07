package org.joelson.mattias.factorio;

import org.joelson.mattias.factorio.calculation.CalculateBase;
import org.joelson.mattias.factorio.products.Product;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class FactorioBasePlanner {

    public static void main(String[] args) {
        int productionGoal = 1800;
        new CalculateBase().calculateProductionPerMinute(new EnumMap<Product, Integer>(Map.of(
                Product.AUTOMATION_SCIENCE_PACK, productionGoal,
                Product.LOGISTIC_SCIENCE_PACK, productionGoal,
                Product.MILITARY_SCIENCE_PACK, productionGoal,
                Product.CHEMICAL_SCIENCE_PACK, productionGoal,
                Product.PRODUCTION_SCIENE_PACK, productionGoal,
                Product.UTILITY_SCIENCE_PACK, productionGoal,
                Product.SPACE_SCIENCE_PACK, productionGoal,
                Product.SATELLITE, 1)),
                Set.of(
                        Product.IRON_ORE,
                        Product.COPPER_ORE,
                        Product.COAL,
                        Product.STONE,
                        Product.CRUDE_OIL,
                        Product.WATER,
                        Product.HEAVY_OIL,
                        Product.LIGHT_OIL,
                        Product.PETROLEUM_GAS,
                        Product.LUBRICANT,
                        Product.IRON_PLATE,
                        Product.COPPER_PLATE,
                        Product.STEEL_PLATE,
                        Product.STONE_BRICK,
                        Product.PLASTIC_BAR,
                        Product.SULFUR,
                        Product.SULFURIC_ACID,
                        Product.BATTERY,
                        Product.ELECTRONIC_CIRCUIT,
                        Product.ADVANCED_CIRCUIT,
                        Product.PROCESSING_UNIT,
                        Product.LOW_DENSITY_STRUCTURE,
                        Product.ROCKET_FUEL,
                        Product.SATELLITE));
    }
}