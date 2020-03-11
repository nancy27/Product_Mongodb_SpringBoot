package com.target.productMongodb.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(value="true")
public class ProductVo {
    @JsonProperty("item")
    private ItemVo itemVo;

    public ProductVo() {
    }

    public ProductVo(ItemVo itemVo) {
        this.itemVo = itemVo;
    }

    public ItemVo getItemVo() {
        return itemVo;
    }

    public void setItemVo(ItemVo itemVo) {
        this.itemVo = itemVo;
    }
}
