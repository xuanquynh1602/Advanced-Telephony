

package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.k22411casampleproject.R;

import models.PaymentMethod;

public class PaymentMethodAdapter extends ArrayAdapter<models.PaymentMethod> {
    Activity context;
    int resource;

    Typeface typeface;
    public PaymentMethodAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context= (Activity) context;
        this.resource=resource;

        this. typeface = Typeface.createFromAsset(
                this.context.getAssets(),
                "fonts/TMC-Ong Do.TTF");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View item=inflater.inflate(resource,null);
        //Lấy đối tượng phương thức thanh toán ở vị trí positon
        PaymentMethod pm=getItem(position);
        TextView txtName=item.findViewById(R.id.txtPaymentMethodName);
        TextView txtDescription=item.findViewById(R.id.txtPaymentMethodDescription);
        txtName.setText(pm.getName());
        txtDescription.setText(pm.getDescription());
        txtName.setTypeface(this.typeface);
        return item;
    }
}
