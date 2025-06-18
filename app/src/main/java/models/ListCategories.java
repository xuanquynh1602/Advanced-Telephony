package models;

import java.util.ArrayList;

public class ListCategories {
    private ArrayList<Category> categories;

    public ListCategories() {
        categories=new ArrayList<>();
    }
    public void addCategory (Category c)
    {
        categories.add(c);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Customer> customers) {
        this.categories = categories;
    }
    public void generate_sample_dataset()
    {
        addCategory(new Category(1, "Áo"));
        addCategory(new Category(2, "Quần"));
        addCategory(new Category(3, "Túi"));
        addCategory(new Category(4, "Váy"));
        addCategory(new Category(5, "Đầm"));
        addCategory(new Category(6, "Nón"));
        addCategory(new Category(7, "Khăn"));
        addCategory(new Category(8, "Phụ kiện"));
    }
}
