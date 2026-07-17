package com.coffeeshop.coffeeshop.prototype;

/**
 * OrderPrototype interface for the Prototype pattern.
 */
public interface OrderPrototype {
    /**
     * Clones the current order object.
     * @return A cloned instance of OrderPrototype.
     */
    OrderPrototype cloneOrder();
}