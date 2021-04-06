package com.regmi.products.dao;

import com.regmi.products.entity.ProductImage;

public interface ProductImageDao {
    ProductImage getProductImage(int id);
    void deleteProductImage(int id);
}
