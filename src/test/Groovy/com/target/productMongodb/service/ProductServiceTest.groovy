package com.target.productMongodb.service

import com.target.productMongodb.Exception.DataNotFoundException
import com.target.productMongodb.domain.CurrentPrice
import com.target.productMongodb.domain.ItemVo
import com.target.productMongodb.domain.Product
import com.target.productMongodb.domain.ProductDescriptionVO
import com.target.productMongodb.domain.ProductExternal
import com.target.productMongodb.domain.ProductVo
import com.target.productMongodb.entity.ProductEntity
import com.target.productMongodb.repository.ProductRepository
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class ProductServiceTest extends Specification {
    RestTemplate restTemplate=Mock(RestTemplate)
    ProductRepository productRepository= Mock(ProductRepository)
    ProductService productService=new ProductService(productRepository,restTemplate)

    def "testing get ProductName when product is found in External API"(){
        given :
        Integer productId= 1
        ProductDescriptionVO productDescriptionVO= new ProductDescriptionVO("Big Tv")
        ItemVo itemVo=new ItemVo()
        itemVo.setProductDescriptionVO(productDescriptionVO)
        ProductVo productVo= new ProductVo();
        productVo.setItemVo(itemVo)
        ProductExternal productExternal=new ProductExternal()
        productExternal.setProductVo(productVo)
        restTemplate.getForObject(_,_)>>productExternal
        when:
        def result= productService.getProductName(productId)
        then:
        result=="Big Tv"
    }
    def"testing get product name when productId is not found in External API"(){
        given:
        Integer productId=12343453
        ProductExternal productExternal=new ProductExternal()
        restTemplate.getForObject(_,_)>>productExternal
        when:
        productService.getProductName(productId)
        then:
        thrown(DataNotFoundException)
    }

    def"testing get product Name when external API calls returns exception"(){
        given:
        Integer productId=2345677
        restTemplate.getForObject(_,_)>>Exception
        when:
        productService.getProductName(productId)
        then:
        thrown(Exception)
    }
def "testing get Product Currency when product is found in db"(){
    given:
    Integer productId=2345677
    CurrentPrice currentPrice=new CurrentPrice(13.23,"USD")
    productRepository.findByProductId(productId)>> new ProductEntity(productId,13.23,"USD")
    when:
    def result= productService.getProductCurrency(productId)
    then:
    result.value==currentPrice.value
    result.currencyCode==currentPrice.currencyCode
}

    def "testing get Product Currency when product is not found in db"(){
        given:
        Integer productId=2345677
        CurrentPrice currentPrice=new CurrentPrice()
        productRepository.findByProductId(productId)>> null
        when:
      def result= productService.getProductCurrency(productId)
        then:
        currentPrice.equals(result)
    }

    def "testing get product currency when exception thrown"(){
        given:
        Integer productId=2345677
        productRepository.findByProductId(productId)>> Exception
        when:
        productService.getProductCurrency(productId)
        then:
        thrown(Exception)
    }

    def "testing update product currency when product is found"(){
        given:Integer productId=2345677
        productRepository.findByProductId(productId)>> new ProductEntity(productId,13.23,"USD")
        ProductEntity productEntity = new ProductEntity(productId,13.50,"US Dollar")
        productRepository.save(_) >>productEntity
        Product product= new Product(productId,"Big TV",new CurrentPrice(13.50,"US Dollar"))
        when:
        def result= productService.updateProductDetails(product,productId)
        then:
        product.equals(result)
    }

    def "testing update product currency when product is not found"(){
        given:
        Integer productId=2345677
        productRepository.findByProductId(productId)>>null
        Product product= new Product(productId,"Big TV",new CurrentPrice(13.50,"US Dollar"))
        when:
        productService.updateProductDetails(product,productId)
        then:
        thrown(DataNotFoundException)
    }

    def "testing update productcurrency method throws exception when updating"(){
        given:Integer productId=2345677
        ProductEntity productEntity = new ProductEntity(productId,13.50,"US Dollar")
        productRepository.findByProductId(productId)>>productEntity
        productRepository.save(_)>> Exception
        Product product= new Product(productId,"Big TV",new CurrentPrice(13.50,"US Dollar"))
        when:
        productService.updateProductDetails(product,productId)
        then:
        thrown(Exception)
    }



}
