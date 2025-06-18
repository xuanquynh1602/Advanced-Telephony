package com.example.k22411casampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.OrdersViewerAdapter;

import java.util.ArrayList;

import connectors.OrdersViewerConnector;
import connectors.SQLiteConnector;
import models.OrdersViewer;

public class OrdersViewerActivity extends AppCompatActivity {

    ListView lvOrdersViewer;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();

    }

    private void addViews() {
        lvOrdersViewer=findViewById(R.id.lvOrdersViewer);
        adapter=new OrdersViewerAdapter(this,R.layout.item_ordersviewer);
        lvOrdersViewer.setAdapter(adapter);

        SQLiteConnector connector=new SQLiteConnector(this);

        OrdersViewerConnector ovc=new OrdersViewerConnector();

        ArrayList<OrdersViewer> dataset=ovc.getAllOrdersViewers(connector.openDatabase());

        adapter.addAll(dataset);
    }
}