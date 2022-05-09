package com.bank;

public class Credit {

    private final double originalValue;
    private double currentValue;

    public Credit(double value) {
        this.originalValue = value;
        this.currentValue = value;
    }

    /**
     * @return Gibt den Startwert zurück.
     */
    public double getCurrentValue() {
        return currentValue;
    }

    /**
     * @return Gibt den aktuellen Wert zurück.
     */
    public double getOriginalValue() {
        return originalValue;
    }

    /**
     * Setze den aktuellen Wert.
     * @param currentValue neuer Wert
     */
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
}
