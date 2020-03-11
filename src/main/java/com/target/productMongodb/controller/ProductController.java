package com.target.productMongodb.controller;

import com.target.productMongodb.domain.Product;
import com.target.productMongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/product/{productId}")
    public Product getProductDetails(@PathVariable Integer productId) throws Exception {
        return productService.getProductDetails(productId);
    }
    @RequestMapping(method = RequestMethod.PUT,value="/product/{productId}")
    public Product updateProductDetails(@RequestBody Product product,@PathVariable Integer productId) throws Exception{
        return productService.updateProductDetails(product,productId);
    }
}
