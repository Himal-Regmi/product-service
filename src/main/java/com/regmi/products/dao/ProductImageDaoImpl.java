package com.regmi.products.dao;

import com.regmi.products.entity.ProductImage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
public class ProductImageDaoImpl implements ProductImageDao{
    @Autowired
    private EntityManager entityManager;

    @Override
    public ProductImage getProductImage(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(ProductImage.class,id);
    }

    @Override
    public void deleteProductImage(int id) {
        Session session = entityManager.unwrap(Session.class);
        ProductImage image= session.get(ProductImage.class,id);
        session.delete(image);
    }
}
