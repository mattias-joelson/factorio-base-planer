package org.joelson.mattias.factorio.products;

public enum Producer {

    ELECTRIC_MINING_DRILL(0.5f),

    PUMPJACK(1.0f),

    OFFSHORE_PUMP(1.0f),

    BOILER(1.0f),

    ELECTRIC_FURNACE(2.0f),

    ASSEMBLING_MACHINE_1(0.5f),
    ASSEMBLING_MACHINE_2(0.75f),
    ASSEMBLING_MACHINE_3(1.25f),

    OIL_REFINERY(1.0f),

    CHEMICAL_PLANT(1.0f),

    ROCKET_SILO(1.0f);

    private Product product;
    private final float craftingSpeed;

    Producer(float craftingSpeed) {
        this.craftingSpeed = craftingSpeed;
    }

    public Product getProduct() {
        return product;
    }

    public float getCraftingSpeed() {
        return craftingSpeed;
    }

    static final Producer[] DRILLS = { ELECTRIC_MINING_DRILL };

    static final Producer[] PUMPJACKS = { PUMPJACK };

    static final Producer[] OFFSHORE_PUMPS = { OFFSHORE_PUMP };

    static final Producer[] BOILERS = { BOILER };

    static final Producer[] FURNACES = { ELECTRIC_FURNACE };

    static final Producer[] ASSEMBLING_MACHINES = { ASSEMBLING_MACHINE_1, ASSEMBLING_MACHINE_2, ASSEMBLING_MACHINE_3 };

    static final Producer[] FLUID_ASSEMBLING_MACHINES = { ASSEMBLING_MACHINE_2, ASSEMBLING_MACHINE_3 };

    static final Producer[] OIL_REFINERIES = { OIL_REFINERY };

    static final Producer[] CHEMICAL_PLANTS = { CHEMICAL_PLANT };

    static final Producer[] PETROLEUM_PRODUCERS = { OIL_REFINERY, CHEMICAL_PLANT };

    static final Producer[] ROCKET_SILOS = { ROCKET_SILO };

    static void setProducts() {
        ELECTRIC_MINING_DRILL.product = Product.ELECTRIC_MINING_DRILL;
        PUMPJACK.product = Product.PUMPJACK;
        OFFSHORE_PUMP.product = Product.OFFSHORE_PUMP;
        BOILER.product = Product.BOILER;
        ELECTRIC_FURNACE.product = Product.ELECTRIC_FURNACE;
        ASSEMBLING_MACHINE_1.product = Product.ASSEMBLING_MACHINE_1;
        ASSEMBLING_MACHINE_2.product = Product.ASSEMBLING_MACHINE_2;
        ASSEMBLING_MACHINE_3.product = Product.ASSEMBLING_MACHINE_3;
        OIL_REFINERY.product = Product.OIL_REFINERY;
        CHEMICAL_PLANT.product = Product.CHEMICAL_PLANT;
        ROCKET_SILO.product = Product.ROCKET_SILO;
    }
}
