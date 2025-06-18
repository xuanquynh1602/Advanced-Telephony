package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Customer;

public class CustomerDetailActivity extends AppCompatActivity {

    EditText edt_customer_id;
    EditText edt_customer_name;
    EditText edt_customer_email;
    EditText edt_customer_phone;
    EditText edt_customer_username;
    EditText edt_customer_password;
    
    Button btn_new;
    Button btn_save;
    Button btn_remove;

    int type=0; //Ben kia truyen qua
    //type=0 --> Xem chi tiết Customer
    //type=1 --> Thêm mới Customer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
        
    }

    private void addEvent() {
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_new();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_save();
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_remove();

            }
        });
    }

    private void do_remove() {
        int id=Integer.parseInt(edt_customer_id.getText().toString());
        Intent intent=getIntent();
        intent.putExtra("CUSTOMER_ID_REMOVE", id);
        setResult(9000,intent);
        finish();
    }

    private void do_save() {
        //khởi tạo đối tượng từ giao diện nhập của khách hàng:
        Customer c=new Customer();
        if(type==0)//Vi la xem chi tiet se có id
        c.setId(Integer.parseInt(edt_customer_id.getText().toString()));
        c.setName((edt_customer_name.getText().toString()));
        c.setEmail((edt_customer_email.getText().toString()));
        c.setPhone((edt_customer_phone.getText().toString()));
        c.setUsername((edt_customer_username.getText().toString()));
        c.setPassword((edt_customer_password.getText().toString()));
        //Lấy intent từ màn hình gọi nó để sử dụng;
        Intent intent=getIntent();
        //đóng gói customer vào intent
        intent.putExtra( "NEW CUSTOMER",c);
        intent.putExtra("TYPE", type);
        //đóng dấu để gửi dữ liệu về
        setResult(1000,intent);
        //sau đó bawst buộc phải đóng màn hình này lại
        //vì điện thoại không cho phesp cùng 1 lúc tại 1 vị trí có 2 màn hình
    }

    private void do_new() {
    }

    private void addView() {
        edt_customer_id=findViewById(R.id.edt_customer_id);
        edt_customer_name=findViewById(R.id.edt_customer_name);
        edt_customer_email=findViewById(R.id.edt_customer_email);
        edt_customer_phone=findViewById(R.id.edt_customer_phone);
        edt_customer_username=findViewById(R.id.edt_customer_username);
        edt_customer_password=findViewById(R.id.edt_customer_password);
        display_customer_details();
        
        btn_new=findViewById(R.id.btn_new);
        btn_save=findViewById(R.id.btn_save);
        btn_remove=findViewById(R.id.btn_remove);
        
    }

    private void display_customer_details() {
        Intent intent=getIntent();
        Customer c=(Customer) intent.getSerializableExtra("SELECTED_CUSTOMER");
        if(c==null){
        type=intent.getIntExtra("TYPE", 1);
            //thêm mới. lúc này ẩn ô nhap ma khach hang di
            edt_customer_id.setVisibility(View.INVISIBLE);
            return;
        }
        edt_customer_id.setText(c.getId()+"");
        edt_customer_name.setText(c.getName());
        edt_customer_email.setText(c.getEmail());
        edt_customer_phone.setText(c.getPhone());
        edt_customer_username.setText(c.getUsername());
        edt_customer_password.setText(c.getPassword());
    }
}