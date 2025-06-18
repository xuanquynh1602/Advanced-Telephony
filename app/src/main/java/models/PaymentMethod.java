package models;

import androidx.annotation.NonNull;

public class PaymentMethod {
    private int Id;
    private String name;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentMethod() {
    }

    public PaymentMethod(int id, String name) {
        Id = id;
        this.name = name;
    }

    public PaymentMethod(int id, String name, String description) {
        Id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
