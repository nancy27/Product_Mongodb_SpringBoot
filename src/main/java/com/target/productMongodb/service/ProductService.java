package com.target.productMongodb.service;

import com.target.productMongodb.Exception.DataNotFoundException;
import com.target.productMongodb.domain.CurrentPrice;
import com.target.productMongodb.domain.Product;
import com.target.productMongodb.domain.ProductExternal;
import com.target.productMongodb.entity.ProductEntity;
import com.target.productMongodb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public Product getProductDetails(Integer productId) throws Exception {
        return new Product(productId,getProductName(productId),getProductCurrency(productId));
    }

    protected String getProductName(Integer productId) throws Exception {
        try {
            ProductExternal productExternal = restTemplate.getForObject("https://redsky.target.com/v2/pdp/tcin/" + productId +
                            "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews, rating_and_review_statistics,question_answer_statistics",
                    ProductExternal.class);
            if (productExternal.getProductVo() != null) {
                if (productExternal.getProductVo().getItemVo() != null) {
                    if (productExternal.getProductVo().getItemVo().getProductDescriptionVO() != null) {
                        return productExternal.getProductVo().getItemVo().getProductDescriptionVO().getTitle();
                    }
                }
            }
            throw new DataNotFoundException("Product not Found ");
        }catch(DataNotFoundException e){throw new DataNotFoundException("Product Not Found");}
        catch(Exception e){throw new Exception("Product Not Found");}
    }

    protected CurrentPrice getProductCurrency(Integer productId) throws Exception {
        try{
        Optional<ProductEntity> optionalProductEntity= Optional.ofNullable(productRepository.findByProductId(productId));
        if(!optionalProductEntity.isPresent()){
            return new CurrentPrice();
        }
        ProductEntity productEntity= optionalProductEntity.get();
        CurrentPrice currentPrice= new CurrentPrice();
        currentPrice.setValue(productEntity.getValue());
        currentPrice.setCurrencyCode(productEntity.getCurrencyCode());
        return currentPrice;
    } catch (Exception e){ throw new Exception();}
    }

    public Product updateProductDetails(Product product,Integer productId) throws Exception {
        try {
            Optional<ProductEntity> productEntityOptional = Optional.ofNullable(productRepository.findByProductId(productId));
            if (!productEntityOptional.isPresent()) {
                throw new DataNotFoundException("Product not found in database");
            }

            ProductEntity productEntity = productEntityOptional.get();
            CurrentPrice currentPrice = product.getCurrentPrice();
            productEntity.setCurrencyCode(currentPrice.getCurrencyCode());
            productEntity.setValue(currentPrice.getValue());
            ProductEntity savedEntity = productRepository.save(productEntity);
             if(savedEntity.getValue().equals(currentPrice.getValue())){
                if(savedEntity.getCurrencyCode().equals(currentPrice.getCurrencyCode()))
                    return product;
          }
             throw  new Exception("Product couldnt be updated");
        }catch(DataNotFoundException e){throw new DataNotFoundException("Product Not Found ");}
        catch(Exception e){ throw new Exception("Exception Found ");}
    }

}
