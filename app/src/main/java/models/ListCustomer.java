package models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ListCustomer {
    private ArrayList<Customer> customers;

    public ListCustomer() {
        customers=new ArrayList<>();
    }
    public void addCustomer (Customer c)
    {
        customers.add(c);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    public void generate_sample_dataset()
    {
        addCustomer(new Customer(1, "Teo", "teo@gmail.com", "0123456789", "teo", "123"));
        addCustomer(new Customer(2, "Ti", "ti@gmail.com", "0766338189", "ti", "456"));
        addCustomer(new Customer(3, "Bi", "bi@gmail.com", "0903191262", "bi", "789"));
        addCustomer(new Customer(4, "Bin", "bin@gmail.com", "0325325355", "bin", "456"));
        addCustomer(new Customer(5, "Tun", "tun@gmail.com", "0766338189", "tun", "456"));
        addCustomer(new Customer(6, "Mun", "mun@gmail.com", "0579624862", "mun", "456"));
        addCustomer(new Customer(7, "Hoa", "hoa@gmail.com", "0269752487", "hoa", "456"));
        addCustomer(new Customer(8, "Hong", "hong@gmail.com", "0958763248", "hong", "456"));
        addCustomer(new Customer(9, "Hue", "hue@gmail.com", "0768745238", "hue", "456"));
        addCustomer(new Customer(10, "Cuc", "cuc@gmail.com", "0985452357", "cuc", "456"));
    }
    public boolean isExisting(Customer c)
    {
        for(Customer cus: customers)
    {
        if(cus.getId()==c.getId() ||
           cus.getPhone().equals(c.getPhone())||
                   cus.getEmail().equalsIgnoreCase(c.getEmail())||
                cus.getUsername().equalsIgnoreCase(c.getUsername())
            )
                return true;
    }
        return false;
    }
    public void getAllCustomers(SQLiteDatabase database)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM Customer",
                null);
        customers.clear();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            String username = cursor.getString(4);
            String password = cursor.getString(5);
            int saveInfor = cursor.getInt(6);
            Customer c=new Customer();
            c.setId(id);
            c.setName(name);
            c.setPhone(phone);
            c.setEmail(email);
            c.setUsername(username);
            c.setPassword(password);
            customers.add(c);
        }
        cursor.close();

    }
}
