package com.example.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.k22411casampleproject.R;

import java.util.List;

import models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    private final List<Product> products;
    Activity context;
    int resource;

    public ProductAdapter(@NonNull Activity context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.context = context;
        this.resource = resource;
        this.products = products;
    }

    static class ViewHolder {
        TextView txtProductId;
        TextView txtProductName;
        TextView txtProductQuantity;
        TextView txtProductPrice;
        ImageView imgProduct;
        ImageView imgCart;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;

        if (item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.txtProductId = item.findViewById(R.id.txtProductId);
            holder.txtProductName = item.findViewById(R.id.txtProductName);
            holder.txtProductQuantity = item.findViewById(R.id.txtProductQuantity);
            holder.txtProductPrice = item.findViewById(R.id.txtProductPrice);
            holder.imgProduct = item.findViewById(R.id.imgProduct);
            holder.imgCart = item.findViewById(R.id.imgCart);
            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }

        Product p = getItem(position);
        if (p != null) {
            holder.txtProductId.setText("ID: " + p.getId());
            holder.txtProductName.setText(p.getName());
            holder.txtProductQuantity.setText("Quantity: " + p.getQuantity());
            holder.txtProductPrice.setText("Price: " + p.getUnitPrice() + " VNĐ");

            if (p.getImage_link() != null && !p.getImage_link().isEmpty()) {
                Glide.with(context)
                        .load(p.getImage_link())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.imgProduct);
            } else {
                holder.imgProduct.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }

        return item;
    }
}
