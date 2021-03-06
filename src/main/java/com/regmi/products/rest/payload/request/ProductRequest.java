package com.regmi.products.rest.payload.request;

public class ProductRequest {
    private String name;

    private String category;

    private String description;

    private int price;

    public ProductRequest(String name, String category, String description, int price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public ProductRequest() {
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
}
