package models;

import java.util.ArrayList;

public class ListProduct {
    private ArrayList<Product> products;

    public ListProduct() {
        products=new ArrayList<>();
    }
    public void addProduct (Product c)
    {
        products.add(c);
    }

    public ArrayList<Product> getProduct() {
        return products;
    }

    public void setProduct(ArrayList<Product> products) {
        this.products = products;
    }
    /*public void generate_sample_dataset()
    {
        addProduct(new Product(43, "Latte", 85, 17.5,118, 310));
        addProduct(new Product(44, "Black Coffee", 90, 12.0,118, 280));
        addProduct(new Product(45, "Iced Coffee", 95, 14.0,118, 290));
        addProduct(new Product(46, "Vanilla", 60, 20.0,119, 250));
        addProduct(new Product(47, "Chocolate", 70, 21.0, 119,260));
        addProduct(new Product(48, "Strawberry", 65, 19.5,119, 255));
        addProduct(new Product(49, "Matcha", 55, 22.0, 119,265));
        addProduct(new Product(50, "Mango Sorbet", 50, 18.0, 119,240));
    }*/
}


