package com.target.productMongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Document(collection = "products")
public class ProductEntity {
    @Id
    private String _id;
    @Field("product_id")
    private Integer productId;
    @Field("value")
    private Double value;
    @Field("currency_code")
    private String currencyCode;

    public ProductEntity() {
    }

    public ProductEntity(Integer productId, Double value, String currencyCode) {
        this.productId = productId;
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
