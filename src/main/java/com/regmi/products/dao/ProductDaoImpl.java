package com.regmi.products.dao;

import com.regmi.products.entity.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> getProducts() {
        Session session = entityManager.unwrap(Session.class);
        Query<Product> query= session.createQuery("from Product order by name",Product.class);
        return query.getResultList();
    }

    @Override
    public Product getProduct(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Product.class,id);
    }

    @Override
    public void save(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.save(product);
    }
    @Override
    public void update(Product product){
        Session session= entityManager.unwrap(Session.class);
        session.update(product);
    }

    @Override
    public void deleteProduct(int id) {
        Session session = entityManager.unwrap(Session.class);

        Product product = session.get(Product.class,id);
        session.delete(product);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        Session session= entityManager.unwrap(Session.class);
        String[] names=name.split(" ");
        StringBuilder sb= new StringBuilder("from Product where name like '%"+name+"%' or category like '%"+name+"%'");
        for(int i=0;i<=names.length-1;i++){
            sb.append(" or name like '%"+names[i]+"%' or category like '%"+names[i]+"%'");
        }
        String query1=sb.toString();

        if(name.equals("")){
            Query<Product> productQuery= session.createQuery("from Product ORDER by price asc");
            return productQuery.getResultList();
        }
        else{
            Query<Product> products= session.createQuery(query1);
            return products.getResultList();
        }
    }
}
