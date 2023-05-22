package com.banxemay.online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.banxemay.online.Util.ConstSaveData;

public class TrangChuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        final EditText edtName = findViewById(R.id.edt_Name);
        final EditText edtEmail = findViewById(R.id.edt_Email);

        Button clickmuahang = findViewById(R.id.clickmuahang);
        clickmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                ConstSaveData.SaveDataTypeStringByName(getApplicationContext(), ConstSaveData.DATA_NAME_USER, edtName.getText().toString());
                ConstSaveData.SaveDataTypeStringByName(getApplicationContext(), ConstSaveData.DATA_EMAIL_USER, edtEmail.getText().toString());
            }
        });

        String name = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_NAME_USER, "");
        String email = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_EMAIL_USER, "");
        edtName.setText(name);
        edtEmail.setText(email);
    }
}