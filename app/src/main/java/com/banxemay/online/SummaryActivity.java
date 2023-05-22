package com.banxemay.online;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.banxemay.online.Database.OrderContract;
import com.banxemay.online.Util.ConstSaveData;

public class SummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public CartAdapter mAdapter;
    public static final int LOADER = 0;
    TextView tvTongTien;
    ListView listView;
    int mTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //MainActivity.tongTien = 0;
        tvTongTien = findViewById(R.id.tvTongTien);
        Button clearthedata = findViewById(R.id.clearthedatabase);
        clearthedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletethedata = getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
                MainActivity.tongTien = 0;
                tvTongTien.setText("Tổng tiền: 0 VNĐ");
            }
        });

        Button btnXacNhan = findViewById(R.id.btn_XacNhan);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(LOADER, null, this);

        TextView tvName = findViewById(R.id.tv_Name);
        String name = ConstSaveData.GetDataTypeStringByName(this, ConstSaveData.DATA_NAME_USER, "");
        tvName.setText("Xin chào, " + name);

        listView = findViewById(R.id.list);
        mAdapter = new CartAdapter(this, tvTongTien, null);
        listView.setAdapter(mAdapter);

        tvTongTien.setText("Tổng tiền: " + String.format("%,d", MainActivity.tongTien) + " VNĐ");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY
        };

        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}