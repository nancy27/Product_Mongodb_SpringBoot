package com.target.productMongodb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemVo {
    @JsonProperty("product_description")
    private ProductDescriptionVO productDescriptionVO;

    public ItemVo() {
    }

    public ItemVo(ProductDescriptionVO productDescriptionVO) {
        this.productDescriptionVO = productDescriptionVO;
    }

    public ProductDescriptionVO getProductDescriptionVO() {
        return productDescriptionVO;
    }

    public void setProductDescriptionVO(ProductDescriptionVO productDescriptionVO) {
        this.productDescriptionVO = productDescriptionVO;
    }
}
