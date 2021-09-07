package org.joelson.mattias.factorio.calculation;

import org.joelson.mattias.factorio.products.Belt;
import org.joelson.mattias.factorio.products.Producer;

public class Preferences {

    public Belt getBelt() {
        return Belt.TRANSPORT_BELT;
    }

    public Producer getMiner() {
        return Producer.ELECTRIC_MINING_DRILL;
    }

    public Producer getPumpjack() {
        return Producer.PUMPJACK;
    }
}
