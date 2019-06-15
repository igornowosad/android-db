package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DbHandler db;

    List<PhoneModel> phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating object to use DBHandler
        db = new DbHandler(this);
        phones = this.db.getAllPhones();
        addDataToDatabase();

    }

    private void readDataFromDatabase() {

        ListView list = (ListView) findViewById(R.id.listview);
        list.setAdapter(new PhoneAdapter());

        // Reading all phones
        Log.d("MainActivity", "Reading all phones..");


        for (PhoneModel i : phones) {
            String log = "Id: " + i.getPhoneId() + " ,Model: " + i.getModel() + " ,Producer: " + i.getProducer();
            // Writing phones to log
            Log.d("MainActivity", log);
        }

    }

    private void addDataToDatabase() {
        // Inserting Phones
        Log.d("MainActivity", "Inserting ..");
        db.addPhone(new PhoneModel(234567, "LG", "Xperia", "4", "http://google.com"));
        db.addPhone(new PhoneModel(234562, "Samsung", "Galaxy", "2", "http://google.com"));
        db.addPhone(new PhoneModel(234547, "Apple", "iPhone", "0", "http://google.com"));
        readDataFromDatabase();
    }

    public void editPhone(View view) {
        Intent intentPhone = new Intent(MainActivity.this ,
                EditPhone.class);
        intentPhone.putExtra("EXTRA_PHONE_ID", view.getTag().toString());
        MainActivity.this.startActivity(intentPhone);
    }

    public void deletePhone(View view) {
        String phoneId =  view.getTag().toString();
        db.deletePhone(phoneId);
        phones = this.db.getAllPhones();
        readDataFromDatabase();
    }

    class PhoneAdapter extends ArrayAdapter<PhoneModel> {
        PhoneAdapter() {
            super(MainActivity.this, R.layout.row, R.id.model, phones);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            TextView producer = (TextView) row.findViewById(R.id.producer);
            TextView model = (TextView) row.findViewById(R.id.model);
            TextView version = (TextView) row.findViewById(R.id.android_version);
            Button editButton = (Button) row.findViewById(R.id.edit);
            Button deleteButton = (Button) row.findViewById(R.id.delete);

            producer.setText(phones.get(position).getProducer());
            model.setText(phones.get(position).getModel());
            version.setText(phones.get(position).getAndroidVersion());
            editButton.setTag(phones.get(position).getPhoneId());
            deleteButton.setTag(phones.get(position).getPhoneId());
            return (row);
        }
    }
}
