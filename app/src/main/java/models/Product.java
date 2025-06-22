package models;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int cateid;
    private int image_id; // chỉ dùng nếu bạn gán ảnh nội bộ
    private String imageLink; // dùng cho ảnh từ URL

    public Product() {}

    public Product(int id, String name, int quantity, double price, int cateid, int image_id, String imageLink) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cateid = cateid;
        this.image_id = image_id;
        this.imageLink = imageLink;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getUnitPrice() {
        return String.valueOf(price);
    }

    // Glide-compatible getter
    public String getImage_link() {
        return imageLink;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " - " + name;
    }

    public void setCateId(int anInt) {
    }
}
