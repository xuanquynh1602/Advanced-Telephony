package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edt_product_id;
    EditText edt_product_name;
    EditText edt_product_quantity;
    EditText edt_product_price;
    EditText edt_product_cateid;
    EditText edt_product_imageid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();

    }

    private void addView() {
        edt_product_id=findViewById(R.id.edt_product_id);
        edt_product_name=findViewById(R.id.edt_product_name);
        edt_product_quantity=findViewById(R.id.edt_product_quantity);
        edt_product_price=findViewById(R.id.edt_product_price);
        edt_product_cateid=findViewById(R.id.edt_product_cateid);
        edt_product_imageid=findViewById(R.id.edt_product_imageid);
        display_product_details();
    }
    private void display_product_details() {
        Intent intent=getIntent();
        Product c=(Product) intent.getSerializableExtra("SELECTED_PRODUCT");
        edt_product_id.setText(String.valueOf(c.getId()));
        edt_product_name.setText(c.getName());
        edt_product_quantity.setText(String.valueOf(c.getQuantity()));
        edt_product_price.setText(String.valueOf(c.getPrice()));
        edt_product_cateid.setText(String.valueOf(c.getCateid()));
        edt_product_imageid.setText(String.valueOf(c.getImage_id()));

    }
}