package connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import models.Category;
import models.Customer;

public class CategoryConnector {
    public ArrayList<Category>getAllCategory(SQLiteDatabase database)
    {
        Cursor cursor = database.rawQuery("SELECT * FROM Category",
                null);
        ArrayList<Category> datasets=new ArrayList<>();
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Category ca=new Category();
            ca.setId(id);
            ca.setName(name);
            datasets.add(ca);
        }
        cursor.close();
        return datasets;
    }
}
