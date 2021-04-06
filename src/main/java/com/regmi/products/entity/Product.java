package com.regmi.products.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private String name;

    private String category;

    private String description;

    private int price;

    private int userId;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<ProductImage> productImages;

    public Product() {
    }

    public Product(String name, String category, String description, int price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public void addImage(ProductImage productImage){
        if(productImages == null){
            productImages = new ArrayList<>();
        }

        productImages.add(productImage);
    }
    public List<String> getImageUrls(){
        List<String> urls=new ArrayList<>();
        if(productImages!=null){
            for(ProductImage productImage:productImages){
                urls.add(productImage.getImageUrl());
            }
        }
        return urls;
    }
    public void addImageUrl(List<String> strings){
        for(String url:strings){
            ProductImage productImage= new ProductImage(url);
            this.addImage(productImage);
        }
    }
}
