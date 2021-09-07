package org.joelson.mattias.factorio.products;

public enum Belt {

    TRANSPORT_BELT(15),
    FAST_TRANSPORT_BELT(30),
    EXPRESS_TRANSPORT_BELT(45);

    private final int speed;

    Belt(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
