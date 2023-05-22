package com.banxemay.online;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.banxemay.online.Database.OrderContract;
import com.banxemay.online.Database.OrderHelper;

public class CartAdapter extends CursorAdapter implements LoaderManager.LoaderCallbacks<Cursor>{
    private TextView tvTongTien;
    public Uri mCurrentCartUri;
    private Context context;

    public CartAdapter(Context context, TextView tvTongTien, Cursor cursor) {
        super(context, cursor, 0);
        this.tvTongTien = tvTongTien;
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView drinkName, price, quantity;
        drinkName = view.findViewById(R.id.drinkNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);
        Button btnHuy = view.findViewById(R.id.btn_Huy);
        btnHuy.setTag(cursor.getPosition());

        final int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofdrink = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofdrink = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

        final String nameofdrink = cursor.getString(name);
        String pricesofdrink = cursor.getString(priceofdrink);
        String quantitysofdrink = cursor.getString(quantityofdrink);

        drinkName.setText(nameofdrink);
        final int priceValue = Integer.parseInt(pricesofdrink.replaceAll(" VNĐ", "")
                .replaceAll("\\.", ""));
        price.setText(String.format("%,d", priceValue) + " VNĐ");
        quantity.setText(", Số lượng: " + quantitysofdrink);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor.moveToPosition((int) view.getTag());
                int id = cursor.getInt(cursor.getColumnIndex(OrderContract.OrderEntry._ID));

                OrderHelper mDatabaseHelper = new OrderHelper(context);
                SQLiteDatabase mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                mSqLiteDatabase.delete(OrderContract.OrderEntry.TABLE_NAME,
                        OrderContract.OrderEntry._ID + " = ?",
                        new String[] {String.valueOf(id)});
                cursor.requery();
                notifyDataSetChanged();

                MainActivity.tongTien -= priceValue;
                tvTongTien.setText("Tổng tiền: " + String.format("%,d", MainActivity.tongTien) + " VNĐ");
            }
        });

        //MainActivity.tongTien += priceValue;
        //tvTongTien.setText("Tổng tiền: " + String.format("%,d", MainActivity.tongTien) + " VNĐ");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY
        };

        return new CursorLoader(context, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
