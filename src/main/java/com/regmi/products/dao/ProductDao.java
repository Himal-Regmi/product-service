package com.regmi.products.dao;

import com.regmi.products.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts();

    Product getProduct(int id);

    void save(Product product);

    void update(Product product);

    void deleteProduct(int id);

    List<Product> getProductsByName(String name);

}
