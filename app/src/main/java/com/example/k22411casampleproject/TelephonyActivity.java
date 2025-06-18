package com.example.k22411casampleproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapters.TelephonyInforAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {

    ListView lvTelephonyInfor;
    TelephonyInforAdapter adapter;
    List<TelephonyInfor> allContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        readAllContacts();
    }

    private void addViews() {
        lvTelephonyInfor = findViewById(R.id.lvTelephonyInfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephonyinfor, new ArrayList<>());
        lvTelephonyInfor.setAdapter(adapter);
    }

    private void readAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        adapter.clear();
        allContacts.clear();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);

                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phone = cursor.getString(phoneIndex);

                TelephonyInfor ti = new TelephonyInfor();
                ti.setDisplayName(name);
                ti.setPhoneNumber(phone);

                adapter.add(ti);
                allContacts.add(ti);
            }
            cursor.close();
        }
    }

    public void callDirect(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void callDialup(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhoneNumber());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void sendSms(TelephonyInfor ti, String content) {
        final SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(ti.getPhoneNumber(), null, content, null, null);
        Toast.makeText(this, "Đã gửi tin nhắn tới " + ti.getPhoneNumber(), Toast.LENGTH_LONG).show();
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void sendSmsPendingIntent(TelephonyInfor ti, String content) {
        final SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        final PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = (result == Activity.RESULT_OK) ? "Send OK" : "Send failed";
                Toast.makeText(TelephonyActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));

        sms.sendTextMessage(ti.getPhoneNumber(), null, content, pendingMsgSent, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_viettel) {
            filterByCarrier("Viettel");
            return true;
        } else if (id == R.id.menu_mobifone) {
            filterByCarrier("MobiFone");
            return true;
        } else if (id == R.id.menu_khac) {
            filterByCarrier("Khác");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterByCarrier(String carrier) {
        List<TelephonyInfor> filteredList = new ArrayList<>();
        for (TelephonyInfor contact : allContacts) {
            if (getCarrier(contact.getPhoneNumber()).equals(carrier)) {
                filteredList.add(contact);
            }
        }
        adapter.updateData(filteredList);
    }

    private String getCarrier(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 3) return "Khác";

        if (phoneNumber.startsWith("+84")) {
            phoneNumber = "0" + phoneNumber.substring(3);
        }

        String prefix = phoneNumber.replaceAll("\\s+", "").substring(0, 3);

        List<String> viettel = Arrays.asList("032", "033", "034", "035", "036", "037", "038", "039", "096", "097", "098", "086");
        List<String> mobifone = Arrays.asList("070", "076", "077", "078", "079", "089", "090", "093");

        if (viettel.contains(prefix)) return "Viettel";
        if (mobifone.contains(prefix)) return "MobiFone";
        return "Khác";
    }
}
