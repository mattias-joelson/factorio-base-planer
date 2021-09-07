package org.joelson.mattias.factorio.calculation;

import org.joelson.mattias.factorio.products.Product;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CalculateBase {

    // TODO production bonus drill pumpjack

    private static final int SEC_PER_MINUTE = 60;

    private static final float PIPE_THROUGHPUT = 1000;
    private static final float BELT_SPEED = 15.0f;

    public void calculateProductionPerMinute(Map<Product, Integer> productions, Set<Product> intermediates) {
        Map<Product, Integer> productionIntermediates = new EnumMap<>(Product.class);
        intermediates.forEach(product -> productionIntermediates.put(product, 0));
        for (Entry<Product, Integer> production : productions.entrySet()) {
            if (production.getValue() > 0) {
                System.out.println("Produce " + production.getValue() + ' ' + production.getKey().getName());
                calculateProductionPerMinute(production.getKey(), production.getValue(), productionIntermediates);
            }
        }

        Product[] products = Product.values();
        boolean oilProcessingHandled = false;
        for (int i = products.length - 1; i >= 0; i -= 1) {
            if (productionIntermediates.containsKey(products[i])) {
                Product product = products[i];
                int goal = productionIntermediates.get(product);
                if (goal > 0) {
                    if (!oilProcessingHandled && Product.OIL_PROCESSING_FLUIDS.contains(product)) {
                        calculateOilCoalProcessing(productionIntermediates);
                        //calculateOilProcessing(productionIntermediates);
                        oilProcessingHandled = true;
                    } else {
                        productionIntermediates.remove(product);
                        System.out.println("Produce " + goal + ' ' + product.getName());
                        calculateProductionPerMinute(product, goal, productionIntermediates);
                    }
                }
            }
        }
    }

    private void calculateProductionPerMinute(Product product, int goal, Map<Product, Integer> productionIntermediates) {
        if (productionIntermediates.containsKey(product)) {
            productionIntermediates.put(product, productionIntermediates.get(product) + goal);
            return;
        }
        float producersNeeded = goal * product.getCraftingTime() / product.getProduced() / product.getProducers()[0].getCraftingSpeed() / 60;
        if (Product.FLUIDS.contains(product)) {
            System.out.println("\t" + goal + ' ' + product.getName() + " per minute: " + producersNeeded + ' '
                    + product.getProducers()[0].getProduct().getName() + " (" + calcPipes(goal) + " pipes)");
        } else {
            System.out.println("\t" + goal + ' ' + product.getName() + " per minute: " + producersNeeded + ' '
                    + product.getProducers()[0].getProduct().getName() + " (" + calcBelts(goal) + " belts)");
        }
        for (Entry<Product, Integer> ingredient : product.getIngredients().entrySet()) {
            int ingredientNeeded = ingredient.getValue() * goal / product.getProduced();
            if (Product.FLUIDS.contains(ingredient.getKey())) {
                System.out.println("\t\tneeds " + ingredientNeeded + ' ' + ingredient.getKey().getName() + " (" + calcPipes(ingredientNeeded) + " pipes)");
            } else {
                System.out.println("\t\tneeds " + ingredientNeeded + ' ' + ingredient.getKey().getName() + " (" + calcBelts(ingredientNeeded) + " belts)");
            }
        }
        for (Entry<Product, Integer> ingredient : product.getIngredients().entrySet()) {
            calculateProductionPerMinute(ingredient.getKey(), ingredient.getValue() * goal / product.getProduced(), productionIntermediates);
        }
    }

    private static float calcBelts(int items) {
        return items / BELT_SPEED / SEC_PER_MINUTE;
    }

    private static float calcPipes(int volume) {
        return volume / PIPE_THROUGHPUT / SEC_PER_MINUTE;
    }

    private void calculateOilProcessing(Map<Product, Integer> productionIntermediates) {
        System.out.println("Using oil pumpjacks");
        int heavyOil = getFluid(productionIntermediates, Product.HEAVY_OIL);
        int lightOil = getFluid(productionIntermediates, Product.LIGHT_OIL);
        int petroleumGas = getFluid(productionIntermediates, Product.PETROLEUM_GAS);

        System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName());
        System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName());
        System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName());

        float refineries  = 0;
        float heavyCrackers = 0;
        float lightCrackers = 0;

        /*if (heavyOil > 0)*/ {
            float heavyRefineries = (heavyOil / 5.0f / SEC_PER_MINUTE);
            // per second
            heavyOil -= heavyRefineries * 5 * SEC_PER_MINUTE;
            lightOil -= heavyRefineries * 9 * SEC_PER_MINUTE;
            petroleumGas -= heavyRefineries * 11 * SEC_PER_MINUTE;
            refineries += heavyRefineries;
            System.out.println("Refineries with no cracking: " + heavyRefineries);
            System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName() + " (-" + heavyRefineries * 5 * SEC_PER_MINUTE + ")");
            System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName() + " (-" + heavyRefineries * 9 * SEC_PER_MINUTE + ")");
            System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName() + " (-" + heavyRefineries * 11 * SEC_PER_MINUTE + ")");
        }


        // x, y, optimal (5 s, 2 s)
        // refineries x * 25, x * 45, x * 55 = 500, 900, 1100 / 5 (5x, 9x, 11x / s)
        // heavy y * 40 -> y * 30  ->  200 -> 150 / 2 (20y, 15y / s)

        // 5x = 20y -> x = 4y -> x = 4, y = 1

        // 4, 1 optimal (5 s, 2 s)
        // refineries 4 * 25, 4 * 45, 4 * 55 = 100, 180, 220 / 5
        // heavy 1 * 40 -> 1 * 30  ->  40 -> 30 / 2
        // 20, 36, 44
        // 0, 51, 44
        /*if (lightOil > 0)*/ {
            float lightRefineriesBlocks = lightOil / 51.0f / SEC_PER_MINUTE;
            float lightRefineries = lightRefineriesBlocks * 4;
            // per second
            lightOil -= 51 * lightRefineriesBlocks * SEC_PER_MINUTE;
            petroleumGas -= 44  * lightRefineriesBlocks * SEC_PER_MINUTE;
            refineries += lightRefineries;
            heavyCrackers += lightRefineriesBlocks;
            System.out.println("Refineries with heavy oil cracking: " + lightRefineries + " " + List.of(lightRefineries * 25 * SEC_PER_MINUTE / 5, lightRefineries * 45 * SEC_PER_MINUTE / 5, lightRefineries * 55 * SEC_PER_MINUTE / 5));
            System.out.println("Chemical plants cracking heavy oil: " + lightRefineriesBlocks + " " + List.of(-lightRefineriesBlocks * 40 * SEC_PER_MINUTE / 2, lightRefineriesBlocks * 30 * SEC_PER_MINUTE / 2, 0));
            System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName());
            System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName() + " (-" + lightRefineriesBlocks * 51 * SEC_PER_MINUTE + ")");
            System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName() + " (-" + lightRefineriesBlocks * 44 * SEC_PER_MINUTE + ")");
        }

        // 20, 5, 17 optimal (5 s, 2 s, 2 s)
        // refineries 20 * 25, 20 * 45, 20 * 55 = 500, 900, 1100 / 5
        // heavy 5 * 40 -> 5 * 30  ->  200 -> 150 / 2
        // light 17 * 30 -> 17 * 20  ->  510 -> 340 / 2
        // 100, 180, 220
        // 0, 255, 220
        // 0, 0, 390
        {
            float petroleumRefineriesBlocks = petroleumGas / 390.0f / SEC_PER_MINUTE;
            float petroleumRefineries = petroleumRefineriesBlocks * 20;
            float petroleumHeavyCrackers = petroleumRefineriesBlocks * 5;
            float petroleumLightCrackers = petroleumRefineriesBlocks * 17;
            // per second
            petroleumGas -= 390  * petroleumRefineriesBlocks * SEC_PER_MINUTE;
            refineries += petroleumRefineries;
            heavyCrackers += petroleumHeavyCrackers;
            lightCrackers += petroleumLightCrackers;
            System.out.println("Refineries with full oil cracking: " + petroleumRefineries+ " " + List.of(petroleumRefineries * 25 * SEC_PER_MINUTE / 5, petroleumRefineries * 45 * SEC_PER_MINUTE / 5, petroleumRefineries * 55 * SEC_PER_MINUTE / 5));
            System.out.println("Chemical plants cracking heavy oil: " + petroleumHeavyCrackers + " " + List.of(-petroleumHeavyCrackers * 40 * SEC_PER_MINUTE / 2, petroleumHeavyCrackers * 30 * SEC_PER_MINUTE / 2, 0));
            System.out.println("Chemical plants cracking light oil: " + petroleumLightCrackers + " " + List.of(0, -petroleumLightCrackers * 30 * SEC_PER_MINUTE / 2, petroleumLightCrackers * 20 * SEC_PER_MINUTE / 2));
            System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName());
            System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName());
            System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName() + " (-" + petroleumRefineriesBlocks * 390 * SEC_PER_MINUTE + ")");
        }

        float heavyOilProduced = refineries * 25 * SEC_PER_MINUTE / 5;
        float lightOilProduced = refineries * 45 * SEC_PER_MINUTE / 5;
        float petroleumGasProduced = refineries * 55 * SEC_PER_MINUTE / 5;
        float crudeOilUsed = refineries * 100 * SEC_PER_MINUTE / 5;
        float waterUsed = refineries * 50 * SEC_PER_MINUTE / 5;
        System.out.println(refineries + " refineries: " + List.of(crudeOilUsed, waterUsed, heavyOilProduced, lightOilProduced, petroleumGasProduced));
        heavyOilProduced -= heavyCrackers * 40 * SEC_PER_MINUTE / 2;
        lightOilProduced += heavyCrackers * 30 * SEC_PER_MINUTE / 2;
        waterUsed += heavyCrackers * 30 * SEC_PER_MINUTE / 2;
        System.out.println(heavyCrackers + " heavy crackers: " + List.of(crudeOilUsed, waterUsed, heavyOilProduced, lightOilProduced, petroleumGasProduced));
        lightOilProduced -= lightCrackers * 30 * SEC_PER_MINUTE / 2;
        petroleumGasProduced += lightCrackers * 20 * SEC_PER_MINUTE / 2;
        waterUsed += lightCrackers * 30 * SEC_PER_MINUTE / 2;
        System.out.println(lightCrackers + " light crackers: " + List.of(crudeOilUsed, waterUsed, heavyOilProduced, lightOilProduced, petroleumGasProduced));

        productionIntermediates.put(Product.WATER, getFluid(productionIntermediates, Product.WATER) + (int) waterUsed);
        productionIntermediates.put(Product.CRUDE_OIL, getFluid(productionIntermediates, Product.CRUDE_OIL) + (int) crudeOilUsed);
    }

    private void calculateOilCoalProcessing(Map<Product, Integer> productionIntermediates) {
        System.out.println("Using oil coal liquefaction");

        int heavyOil = getFluid(productionIntermediates, Product.HEAVY_OIL);
        int lightOil = getFluid(productionIntermediates, Product.LIGHT_OIL);
        int petroleumGas = getFluid(productionIntermediates, Product.PETROLEUM_GAS);

        System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName());
        System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName());
        System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName());

        float refineries  = 0;
        float heavyCrackers = 0;
        float lightCrackers = 0;

        /*if (heavyOil > 0)*/ {
            float heavyRefineries = (heavyOil / 15.0f / SEC_PER_MINUTE);
            // per second
            heavyOil -= heavyRefineries * 15 * SEC_PER_MINUTE;
            lightOil -= heavyRefineries * 4 * SEC_PER_MINUTE;
            petroleumGas -= heavyRefineries * 2 * SEC_PER_MINUTE;
            refineries += heavyRefineries;
            System.out.println("Refineries with no cracking: " + heavyRefineries + " " + List.of(heavyRefineries * 75 * SEC_PER_MINUTE / 5, heavyRefineries * 20 * SEC_PER_MINUTE / 5, heavyRefineries * 10 * SEC_PER_MINUTE / 5));
            System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName() + " (-" + heavyRefineries * 75 * SEC_PER_MINUTE / 5 + ")");
            System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName() + " (-" + heavyRefineries * 20 * SEC_PER_MINUTE / 5 + ")");
            System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName() + " (-" + heavyRefineries * 10 * SEC_PER_MINUTE / 5 + ")");
        }


        // x, y optimal (5s, 2s)
        // refineries x * 75, x * 20, x * 10 / 5s = 15x, 4x, 2x / s
        // heavy y * 40 -> y * 30 / 2s ->  20y, 15y / s

        // 3x = 4y -> x = 4, y = 3

        // 4, 3 optimal (5 s, 2 s)
        // refineries 4 * 75, 4 * 20, 4 * 10 = 300, 80, 40 / 5s = 60, 16, 8 / s
        // heavy 3 * 40 -> 3 * 30  ->  120 -> 90 / 2s = 60 -> 45 / s
        // 60, 16, 8
        // 0, 61, 8
        /*if (lightOil > 0)*/ {
            float lightRefineriesBlocks = lightOil / 61.0f / SEC_PER_MINUTE;
            float lightRefineries = lightRefineriesBlocks * 4;
            float lightHeavyCrackers = lightRefineriesBlocks * 3;
            // per second
            lightOil -= 61 * lightRefineriesBlocks * SEC_PER_MINUTE;
            petroleumGas -= 8  * lightRefineriesBlocks * SEC_PER_MINUTE;
            refineries += lightRefineries;
            heavyCrackers += lightHeavyCrackers;
            System.out.println("Refineries with heavy oil cracking: " + lightRefineries + " " + List.of(lightRefineries * 75 * SEC_PER_MINUTE / 5, lightRefineries * 20 * SEC_PER_MINUTE / 5, lightRefineries * 10 * SEC_PER_MINUTE / 5));
            System.out.println("Chemical plants cracking heavy oil: " + lightHeavyCrackers + " " + List.of(-lightHeavyCrackers * 40 * SEC_PER_MINUTE / 2, lightHeavyCrackers * 30 * SEC_PER_MINUTE / 2, 0));
            System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName());
            System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName() + " (- " + lightRefineriesBlocks * 61 * SEC_PER_MINUTE + ")");
            System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName() + " (- " + lightRefineriesBlocks * 8 * SEC_PER_MINUTE + ")");
        }

        // x, y, z optimal (5s, 2s, 2s)
        // refineries x * 75, x * 20, x * 10 / 5s = 15x, 4x, 2x / s
        // heavy y * 40 -> y * 30 / 2s ->  20y, 15y / s
        // light z * 30 -> z * 20 / 2s ->  15z, 10z / s

        // 15x = 20y, 4x + 15y = 15 z
        // 3x = 4y -> x = 4/3 y
        // 16/3 y + 15 y = 15 z -> 16 y + 45 y = 15 z -> 61 y = 45 z
        // z = 61, y = 45 -> x = 60

        // 60, 45, 61 optimal (5 s, 2 s, 2 s)
        // refineries 60 * 75, 60 * 20, 60 * 10 = 4500, 1200, 600 / 5 = 900, 240, 120
        // heavy 45 * 40 -> 45 * 30  ->  1800 -> 1350 / 2 = 900 -> 675
        // light 61 * 30 -> 61 * 20  ->  1830 -> 1220 / 2 = 915 -> 610
        // 900, 240, 120
        // 0, 915, 120
        // 0, 0, 730
        {
            float petroleumRefineriesBlocks = petroleumGas / 730.0f / SEC_PER_MINUTE;
            float petroleumRefineries = petroleumRefineriesBlocks * 60;
            float petroleumHeavyCrackers = petroleumRefineriesBlocks * 45;
            float petroleumLightCrackers = petroleumRefineriesBlocks * 61;
            // per second
            petroleumGas -= 730  * petroleumRefineriesBlocks * SEC_PER_MINUTE;
            refineries += petroleumRefineries;
            heavyCrackers += petroleumHeavyCrackers;
            lightCrackers += petroleumLightCrackers;
            System.out.println("Refineries with full oil cracking: " + petroleumRefineries + " " + List.of(petroleumRefineries * 75 * SEC_PER_MINUTE / 5, petroleumRefineries * 20 * SEC_PER_MINUTE / 5, petroleumRefineries * 10 * SEC_PER_MINUTE / 5));
            System.out.println("Chemical plants cracking heavy oil: " + petroleumHeavyCrackers + " " + List.of(-petroleumHeavyCrackers * 40 * SEC_PER_MINUTE / 2, petroleumHeavyCrackers * 30 * SEC_PER_MINUTE / 2, 0));
            System.out.println("Chemical plants cracking light oil: " + petroleumLightCrackers + " " + List.of(0, -petroleumLightCrackers * 30 * SEC_PER_MINUTE / 2, petroleumLightCrackers * 20 * SEC_PER_MINUTE / 2));
            System.out.println("Needs " + heavyOil + " " + Product.HEAVY_OIL.getName());
            System.out.println("Needs " + lightOil + " " + Product.LIGHT_OIL.getName());
            System.out.println("Needs " + petroleumGas + " " + Product.PETROLEUM_GAS.getName() + " (-" + petroleumRefineriesBlocks * 730 * SEC_PER_MINUTE + ")");
        }

        float heavyOilProduced = refineries * 75 * SEC_PER_MINUTE / 5;
        float lightOilProduced = refineries * 20 * SEC_PER_MINUTE / 5;
        float petroleumGasProduced = refineries * 10 * SEC_PER_MINUTE / 5;
        float coalUsed = refineries * 10 * SEC_PER_MINUTE / 5;
        float steamUsed = refineries * 50 * SEC_PER_MINUTE / 5;
        System.out.println(refineries + " refineries: " + List.of(coalUsed, steamUsed, heavyOilProduced, lightOilProduced, petroleumGasProduced));
        heavyOilProduced -= heavyCrackers * 40 * SEC_PER_MINUTE / 2;
        lightOilProduced += heavyCrackers * 30 * SEC_PER_MINUTE / 2;
        float waterUsed = heavyCrackers * 30 * SEC_PER_MINUTE / 2;
        System.out.println(heavyCrackers + " heavy crackers: " + List.of(coalUsed, steamUsed, heavyOilProduced, lightOilProduced, petroleumGasProduced));
        lightOilProduced -= lightCrackers * 30 * SEC_PER_MINUTE / 2;
        petroleumGasProduced += lightCrackers * 20 * SEC_PER_MINUTE / 2;
        waterUsed += lightCrackers * 30 * SEC_PER_MINUTE / 2;
        System.out.println(lightCrackers + " light crackers: " + List.of(coalUsed, steamUsed, heavyOilProduced, lightOilProduced, petroleumGasProduced));
        System.out.println(List.of("Water", waterUsed, "Coal", coalUsed, "Steam", steamUsed));

        productionIntermediates.put(Product.WATER, getFluid(productionIntermediates, Product.WATER) + (int) waterUsed);
        productionIntermediates.put(Product.COAL, getFluid(productionIntermediates, Product.COAL) + (int) coalUsed);
        productionIntermediates.put(Product.STEAM, getFluid(productionIntermediates, Product.STEAM) + (int) steamUsed);
        //System.out.println("Needs " + steamUsed + " steam");
    }


    private int getFluid(Map<Product, Integer> productionIntermediates, Product fluid) {
        if (productionIntermediates.containsKey(fluid)) {
            return productionIntermediates.remove(fluid);
        }
        return 0;
    }
}
