package com.target.productMongodb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductExternal {
    @JsonProperty("product")
    private ProductVo productVo;

    public ProductExternal() {
    }

    public ProductExternal(ProductVo productVo) {
        this.productVo = productVo;
    }

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }
}
