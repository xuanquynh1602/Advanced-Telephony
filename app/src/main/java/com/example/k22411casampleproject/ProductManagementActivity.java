package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.ProductAdapter;

import java.util.ArrayList;

import connectors.CategoryConnector;
import connectors.ProductConnector;
import models.Category;
import models.ListProduct;
import models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    ProductAdapter adapter;

    ListView lvProduct;

    ListProduct lc=new ListProduct();
    MenuItem menu_new_product;
    MenuItem menu_broadcast_advertising;

    MenuItem menu_help;

    ProductConnector pc;
    int selectedCategoryId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        selectedCategoryId = getIntent().getIntExtra("SELECTED_CATEGORY_ID", -1);
        if (selectedCategoryId == -1) {
            Toast.makeText(this, "Không có danh mục nào được chọn", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        addViews();
        addEvents();
    }

    private void addViews() {
        lvProduct = findViewById(R.id.lvProduct);

        pc = new ProductConnector();
        connectors.SQLiteConnector connector = new connectors.SQLiteConnector(this);
        ArrayList<Product> datasets = pc.getProductsByCategoryId(connector.openDatabase(), selectedCategoryId);

        // Khởi tạo adapter với danh sách dữ liệu
        adapter = new ProductAdapter(
                ProductManagementActivity.this,
                R.layout.item_advanced_product,
                datasets
        );

        /*lc.generate_sample_dataset();
        adapter.addAll(lc.getProduct());*/
        lvProduct.setAdapter(adapter);

        menu_new_product=findViewById(R.id.menu_new_product);
        menu_broadcast_advertising=findViewById(R.id.menu_broadcast_advertising);
        menu_help=findViewById(R.id.menu_help);
    }

    private void addEvents() {
        //Nhấn vào product thì mở chi tiết
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product c=adapter.getItem(position);
                openProductDetailActivity(c);
            }
        });
    }

    private void openProductDetailActivity(Product c) {
        Intent intent=new Intent(ProductManagementActivity.this,
                ProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT",c);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_new_product)
        {
            Toast.makeText(this,"Mở màn hình thêm sản phẩm mới",Toast.LENGTH_LONG).show();
            openNewProductActivity();
        }
        else if(item.equals(menu_broadcast_advertising))
        {
            Toast.makeText(this, "Bắn tim quảng cáo tới hàng loạt khách hàng", Toast.LENGTH_LONG).show();
            //Tìm hiểu firebase Cloud message
        }
        else if (item.getItemId()==R.id.menu_help)
        {
            Toast.makeText(this, "Mở màn hình trợ giúp khách hàng", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNewProductActivity() {
        //hôm sau làm. Nhấn vào đôi tượng thì mở màn hình chi tiết lên

    }
}