package connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import models.Category;
import models.Product;

public class ProductConnector {
    public ArrayList<Product> getProductsByCategoryId(SQLiteDatabase db, int categoryId) {
        ArrayList<Product> result = new ArrayList<>();

        // Truy vấn lấy các sản phẩm theo category id
        String sql = "SELECT Id, Name, Quantity, Price, CateId, ImageLink FROM Product WHERE CateId=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(categoryId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Product p = new Product();

                p.setId(cursor.getInt(0));
                p.setName(cursor.getString(1));
                p.setQuantity(cursor.getInt(2));
                p.setPrice(cursor.getDouble(3));
                p.setCateId(cursor.getInt(4));
                p.setImageLink(cursor.getString(5));
                result.add(p);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return result;
    }

}
