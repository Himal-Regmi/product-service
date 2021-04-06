package com.regmi.products.service;

import com.regmi.products.dao.ProductImageDao;
import com.regmi.products.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    private ProductImageDao productImageDao;

    @Autowired
    public ProductImageServiceImpl(ProductImageDao productImageDao) {
        this.productImageDao = productImageDao;
    }

    @Override
    public ProductImage getProductImage(int id) {
        return productImageDao.getProductImage(id);
    }

    @Override
    public void deleteProductImage(int id) {
        productImageDao.deleteProductImage(id);
    }
}
