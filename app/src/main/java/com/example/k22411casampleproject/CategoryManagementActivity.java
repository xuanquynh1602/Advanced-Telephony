package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import connectors.CategoryConnector;
import connectors.SQLiteConnector;
import models.Category;
import models.ListCategories;
import models.Product;

public class CategoryManagementActivity extends AppCompatActivity {
    ListView lvCategory;
    ListCategories lc=new ListCategories();
    ArrayAdapter<Category> adapter;

    CategoryConnector cac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvents();
    }

    private void addView() {
        lvCategory=findViewById(R.id.lvCategory);
        adapter=new ArrayAdapter<>(
                CategoryManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvCategory.setAdapter(adapter);
//        lc.generate_sample_dataset();
//        adapter.addAll(lc.getCategories());

        cac = new CategoryConnector();
        connectors.SQLiteConnector connector = new connectors.SQLiteConnector(this);
        ArrayList<Category> datasets = cac.getAllCategory(connector.openDatabase());
        adapter.addAll(datasets);
    }

    private void addEvents() {
        //Nhấn vào category thì mở chi tiết
        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category c=adapter.getItem(position);
                openProductManagementActivity(c);
            }
        });
    }

    private void openProductManagementActivity(Category c) {
        Intent intent = new Intent(CategoryManagementActivity.this, ProductManagementActivity.class);
        intent.putExtra("SELECTED_CATEGORY_ID", c.getId()); // Truyền ID thay vì object
        startActivity(intent);
    }
}