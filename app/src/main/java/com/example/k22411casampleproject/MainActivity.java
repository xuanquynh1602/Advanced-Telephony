package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imgEmployee;
    TextView txtEmployee;

    ImageView imgCustomer;
    TextView txtCustomer;

    ImageView imgCategory;
    TextView txtCategory;
    ImageView imgProduct;
    TextView txtProduct;
    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;

    ImageView imgPaymentMethod;
    TextView txtPaymentMethod;

    ImageView imgOrder;
    TextView txtOrder;
    ImageView imgContact;
    TextView txtContact;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addView();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        imgEmployee=findViewById(R.id.imgEmployee);
        txtEmployee=findViewById(R.id.txtEmployee);
        imgCustomer=findViewById(R.id.imgCustomer);
        txtCustomer=findViewById(R.id.txtCustomer);
        imgCategory=findViewById(R.id.imgCategory);
        txtCategory=findViewById(R.id.txtCategory);
        imgProduct=findViewById(R.id.imgProduct);
        txtProduct=findViewById(R.id.txtProduct);
        imgAdvancedProduct=findViewById(R.id.imgAdvancedProduct);
        txtAdvancedProduct=findViewById(R.id.txtAdvancedProduct);
        imgPaymentMethod=findViewById(R.id.imgPaymentMethod);
        txtPaymentMethod=findViewById(R.id.txtPaymentMethod);
        imgOrder=findViewById(R.id.imgOrder);
        txtOrder=findViewById(R.id.txtOrder);
        imgContact=findViewById(R.id.imgContact);
        txtContact=findViewById(R.id.txtContact);


    }

    private void addEvents() {
        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployeeManagementActivity();
            }
        });
        txtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmployeeManagementActivity();
            }
                                       }
        );
        imgCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { openCustomerManagementActivity();}

        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openCustomerManagementActivity();}
        });
        imgCategory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { openCategoryManagementActivity();}

        });
        txtCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openCategoryManagementActivity();}
        });
        imgProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { openProductManagementActivity();}

        });
        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { openProductManagementActivity();}
        });
        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });
        txtPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        imgPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrdersViewerActivity();
            }
        });
        imgContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        txtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
    }

    private void openTelephonyActivity() {
        Intent intent=new Intent(MainActivity.this, TelephonyActivity.class);
        startActivity(intent);
    }

    private void openOrdersViewerActivity() {
        Intent intent=new Intent(MainActivity.this, OrdersViewerActivity.class);
        startActivity(intent);
    }

    private void openProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }

    private void openEmployeeManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }
    private void openCustomerManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }
    private void openCategoryManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CategoryManagementActivity.class);
        startActivity(intent);
    }
    private void openAdvancedProductManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
        startActivity(intent);
    }
    private void openPaymentMethodActivity()
    {
        Intent intent=new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
        startActivity(intent);
    }

}