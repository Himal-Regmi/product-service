package com.regmi.products.rest.payload.response;

import java.util.List;

public class ProductResponse {
    private int id;

    private String name;

    private String category;

    private String description;

    private int price;

    private int userId;

    private List<String> ImagesUrls;

    public ProductResponse(int id, String name, String category, String description, int price, int userId,
                           List<String> imagesUrls) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.userId = userId;
        ImagesUrls = imagesUrls;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<String> getImagesUrls() {
        return ImagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        ImagesUrls = imagesUrls;
    }
}
