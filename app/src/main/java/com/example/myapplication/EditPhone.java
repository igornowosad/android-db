package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditPhone extends Activity {

    private DbHandler db;
    private int phoneId;
    PhoneModel phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_phone);

        db = new DbHandler(this);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Intent intentMain = new Intent(EditPhone.this, MainActivity.class);
            EditPhone.this.startActivity(intentMain);
        }
        phoneId = Integer.parseInt((String) getIntent().getSerializableExtra("EXTRA_PHONE_ID"));
        phone = this.db.getPhone(phoneId);

        prepareView();
    }

    private void prepareView() {
        EditText producerEdit = findViewById(R.id.producer);
        EditText modelEdit = findViewById(R.id.model);
        EditText androidVersionEdit = findViewById(R.id.android_version);
        EditText urlEdit = findViewById(R.id.url);

        Button submitButton = findViewById(R.id.save);
        Button cancelButton = findViewById(R.id.cancel);

        setInputsValues(producerEdit, modelEdit, androidVersionEdit, urlEdit);

        addInputListeners(producerEdit, modelEdit, androidVersionEdit, urlEdit);

        addButttonsListeners(submitButton, cancelButton);
    }

    private void setInputsValues(EditText producerEdit, EditText modelEdit, EditText androidVersionEdit, EditText urlEdit) {
        producerEdit.setText(phone.getProducer());
        modelEdit.setText(phone.getModel());
        androidVersionEdit.setText(phone.getAndroidVersion());
        urlEdit.setText(phone.getUrl());
    }

    private void addButttonsListeners(Button submitButton, Button cancelButton) {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updatePhone(phone);
                Intent intentMain = new Intent(EditPhone.this, MainActivity.class);
                EditPhone.this.startActivity(intentMain);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(EditPhone.this, MainActivity.class);
                EditPhone.this.startActivity(intentMain);
            }
        });
    }

    private void addInputListeners(EditText producerEdit, EditText modelEdit, EditText androidVersionEdit, EditText urlEdit) {
        producerEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phone.setProducer(editable.toString());
            }
        });

        modelEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phone.setModel(editable.toString());
            }
        });

        androidVersionEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phone.setAndroidVersion(editable.toString());
            }
        });

        urlEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phone.setUrl(editable.toString());
            }
        });
    }

}
