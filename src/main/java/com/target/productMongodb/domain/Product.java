package com.target.productMongodb.domain;

import java.util.Objects;

public class Product {
    private Integer productId;
    private String productName;
    private CurrentPrice currentPrice;

    public Product() {
    }

    public Product(Integer productId, String productName, CurrentPrice currentPrice) {
        this.productId = productId;
        this.productName = productName;
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(currentPrice, product.currentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, currentPrice);
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CurrentPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(CurrentPrice currentPrice) {
        this.currentPrice = currentPrice;
    }
}
