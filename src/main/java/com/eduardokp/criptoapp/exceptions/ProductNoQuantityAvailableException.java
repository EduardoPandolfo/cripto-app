package com.eduardokp.criptoapp.exceptions;

public class ProductNoQuantityAvailableException extends IllegalArgumentException {
    public final static String MSG = "Product has no such quantity available";

    public ProductNoQuantityAvailableException() {
        super(MSG);
    }
}
