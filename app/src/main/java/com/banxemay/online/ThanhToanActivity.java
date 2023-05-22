package com.banxemay.online;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.banxemay.online.Database.OrderContract;
import com.banxemay.online.Util.ConstSaveData;

public class ThanhToanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        final EditText edtName = findViewById(R.id.edt_Name);
        final EditText edtEmail = findViewById(R.id.edt_Email);
        final EditText edt_DiaChi = findViewById(R.id.edt_DiaChi);
        final EditText edt_SDT = findViewById(R.id.edt_SDT);

        Button btn_ThanhToan = findViewById(R.id.btn_ThanhToan);
        btn_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletethedata = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
                MainActivity.tongTien = 0;

                ConstSaveData.SaveDataTypeStringByName(getApplicationContext(), ConstSaveData.DATA_NAME_USER, edtName.getText().toString());
                ConstSaveData.SaveDataTypeStringByName(getApplicationContext(), ConstSaveData.DATA_EMAIL_USER, edtEmail.getText().toString());
                ConstSaveData.SaveDataTypeStringByName(getApplicationContext(), ConstSaveData.DATA_DIACHI_USER, edt_DiaChi.getText().toString());
                ConstSaveData.SaveDataTypeStringByName(getApplicationContext(), ConstSaveData.DATA_SDT_USER, edt_SDT.getText().toString());

                Toast.makeText(getApplicationContext(), "Bạn đã thanh toán thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        String name = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_NAME_USER, "");
        String email = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_EMAIL_USER, "");
        if(!name.equals("") && (edtName.getText().toString().equals("") || edtName.getText().toString() == null)){
            edtName.setText(name);
        }
        if(!email.equals("") && (edtEmail.getText().toString().equals("") || edtEmail.getText().toString() == null)){
            edtEmail.setText(email);
        }

        String diachi = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_DIACHI_USER, "");
        String sdt = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_SDT_USER, "");
        if(!diachi.equals("") && (edt_DiaChi.getText().toString().equals("") || edt_DiaChi.getText().toString() == null)){
            edt_DiaChi.setText(diachi);
        }
        if(!sdt.equals("") && (edt_SDT.getText().toString().equals("") || edt_SDT.getText().toString() == null)){
            edt_SDT.setText(sdt);
        }
    }
}