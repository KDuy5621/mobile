package com.fsoft.mobile.Product;

public class ProductNotFoundException extends Throwable{
    public ProductNotFoundException(String mess) {
        super(mess);
    }
}
