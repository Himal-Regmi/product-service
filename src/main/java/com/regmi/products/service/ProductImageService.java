package com.regmi.products.service;

import com.regmi.products.entity.ProductImage;

public interface ProductImageService {
    ProductImage getProductImage(int id);
    void deleteProductImage(int id);
}
