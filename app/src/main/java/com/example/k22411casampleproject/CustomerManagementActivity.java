package com.example.k22411casampleproject;

import android.app.ComponentCaller;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Customer;
import models.ListCustomer;
import models.ListEmployee;

public class CustomerManagementActivity extends AppCompatActivity {
    ListView lvCustomer;
    ListCustomer lc=new ListCustomer();
    ArrayAdapter<Customer> adapter;

    MenuItem menu_new_customer;
    MenuItem menu_broadcast_advertising;

    MenuItem menu_help;

final int ID_CREATE_NEW_CUSTOMER=1;
final int IC_UPDATE_CUSTOMER=2;

    String DATABASE_NAME="SalesDatabase.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvents();
    }

    private void addEvents() {
        //Nhấn vào customer thì mở chi tiết
        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer c=adapter.getItem(position);
                openCustomerDetailActivity(c);
            }
        });
    }

    private void openCustomerDetailActivity(Customer c) {
        Intent intent=new Intent(CustomerManagementActivity.this,
                CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER",c);
        intent.putExtra("TYPE",0);
        //startActivity(intent);
        startActivityForResult(intent,ID_CREATE_NEW_CUSTOMER);
    }

    private void addView() {
        lvCustomer=findViewById(R.id.lvCustomer);
        adapter=new ArrayAdapter<>(
                CustomerManagementActivity.this,
                android.R.layout.simple_list_item_1);

        //lc.generate_sample_dataset();
        database = openOrCreateDatabase(DATABASE_NAME,
                MODE_PRIVATE,null);
        lc.getAllCustomers(database);

        adapter.addAll(lc.getCustomers());

        lvCustomer.setAdapter(adapter);

        menu_new_customer=findViewById(R.id.menu_new_customer);
        menu_broadcast_advertising=findViewById(R.id.menu_broadcast_advertising);
        menu_help=findViewById(R.id.menu_help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_new_customer)
        {
            Toast.makeText(this,"Mở màn hình thêm khách hàng mới",Toast.LENGTH_LONG).show();
            openNewCustomerActivity();
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

    private void openNewCustomerActivity() {
        //hôm sau làm. Nhấn vào đôi tượng thì mở màn hình chi tiết lên
        Intent intent=new Intent(CustomerManagementActivity.this,
                CustomerDetailActivity.class
        );
        intent.putExtra("TYPE",1);
        startActivityForResult(intent,ID_CREATE_NEW_CUSTOMER);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ID_CREATE_NEW_CUSTOMER && resultCode==1000)
        {
            //Lấy kết quả ra:
            Customer c= (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            // Tới đây có 2 tình huống lưu mới hay lưu cập nhật?
            int type=data.getIntExtra("TYPE", 1);
            if (type==1)
            process_save_customer(c);
            else process_save_update_customer(c);
        }
        else if(requestCode==ID_CREATE_NEW_CUSTOMER && resultCode==9000)
        {
            int cust_id=data.getIntExtra("CUSTOMER_ID_REMOVE", -1);
            if(cust_id!=-1)
            {
                process_remove_customer(cust_id);
            }
        }
    }

    private void process_remove_customer(int custId) {
        int id = database.delete("Customer", "Id=?", new String[]{custId+""});
        if(id>0)
        {
            lc.getAllCustomers(database);
            adapter.clear();
            adapter.addAll(lc.getCustomers());
        }
    }

    private void process_save_update_customer(Customer c) {
        ContentValues values=new ContentValues();
        values.put( "Name", c.getName());
        values.put( "Phone", c.getPhone());
        values.put("Email", c.getEmail());
        values.put( "UserName", c.getUsername());
        values.put("PassWord", c.getPassword());
        values.put("SaveInfor", 0);
        int id= database.update("Customer", values, "Id=?", new String[]{c.getId()+""});
        if(id>0)
        {
            lc.getAllCustomers(database);
            adapter.clear();
            adapter.addAll(lc.getCustomers());
        }
    }

    private void process_save_customer( Customer c) {
        /*boolean result=lc.isExisting(c);
        if(result==true)//tức là đã tồn tại
            return;*///không thêm mới
        //còn nếu ta muốn cập nhật thì viết code cập nhật
        //các mã lệnh dưới ddaaya là thêm mới Customer;
        /*lc.addCustomer(c);
        adapter.clear();
        adapter.addAll(lc.getCustomers());*/
        //xử lý thêm Customer (c) vào CSDL SQLite
        ContentValues values=new ContentValues();
        values.put( "Name", c.getName());
        values.put( "Phone", c.getPhone());
        values.put("Email", c.getEmail());
        values.put( "UserName", c.getUsername());
        values.put("PassWord", c.getPassword());
        values.put("SaveInfor", 0);
        long insertResult=database.insert("Customer", null, values);
        if(insertResult>0)//Là thêm thành công
        {
            //nạp lại dữ liệu từ bảng Customer lên cho giao diện
            lc.getAllCustomers(database);
            adapter.clear();
            adapter.addAll(lc.getCustomers());
        }
    }
}