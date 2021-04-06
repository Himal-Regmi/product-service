package com.regmi.products.rest.payload;

import com.regmi.products.entity.Product;
import com.regmi.products.rest.payload.request.ProductRequest;
import com.regmi.products.rest.payload.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class PayloadMapper {
    public List<ProductResponse> getProductResponses(List<Product> products){
        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product:products){
            productResponses.add(new ProductResponse(product.getId(),product.getName(),product.getCategory(),
                    product.getDescription(),product.getPrice(),product.getUserId(),product.getImageUrls()));
        }

        return productResponses;
    }
    public ProductResponse getProductResponse(Product product){
        return new ProductResponse(product.getId(),product.getName(),product.getCategory(),
                product.getDescription(),product.getPrice(),product.getUserId(),product.getImageUrls());
    }
    public  Product getProductFromRequest(ProductRequest productRequest){
        return new Product(productRequest.getName(),productRequest.getCategory(),productRequest.getDescription(),
                productRequest.getPrice());
    }
}
