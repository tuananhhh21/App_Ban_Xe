package com.banxemay.online;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.banxemay.online.Database.OrderContract;

public class InfoActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static int indexProduct;

    ImageView imageView, imageViewCart;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, drinnkName, descriptioninfo, coffeePrice;
    CheckBox addToppings, addExtraCream;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;
    Model product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        product = MainActivity.modelList.get(indexProduct);

        imageView = findViewById(R.id.imageViewInfo);
        imageViewCart = findViewById(R.id.imageViewCart);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity  = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        drinnkName = findViewById(R.id.drinkNameinInfo);
        descriptioninfo = findViewById(R.id.descriptioninfo);
        coffeePrice = findViewById(R.id.coffeePrice);
        addtoCart = findViewById(R.id.addtocart);

        drinnkName.setText(product.mDrinkName);
        descriptioninfo.setText(product.mDrinkDetail);
        imageView.setImageResource(product.mDrinkPhoto);
        coffeePrice.setText(String.format("%,d", product.getPrice()) + " VNĐ");

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, SummaryActivity.class);
                startActivity(intent);

                SaveCart();
            }
        });

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coffee price
                int basePrice = product.getPrice();
                quantity++;
                displayQuantity();
                int coffePrice = basePrice * quantity;
                String setnewPrice = String.format("%,d", coffePrice);
                coffeePrice.setText(setnewPrice + " VNĐ");
            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = product.getPrice();
                // because we dont want the quantity go less than 0
                if (quantity == 0) {
                    Toast.makeText(InfoActivity.this, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int coffePrice = basePrice * quantity;
                    String setnewPrice = String.format("%,d", coffePrice);
                    coffeePrice.setText(setnewPrice + " VNĐ");
                }
            }
        });
    }

    private boolean SaveCart() {

        // getting the values from our views
        String name = drinnkName.getText().toString();
        String price = coffeePrice.getText().toString().replaceAll("\\,", "");
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);

        final int priceValue = Integer.parseInt(price.replaceAll(" VNĐ", "")
                .replaceAll("\\.", ""));
        MainActivity.tongTien += priceValue;

        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri==null) {
                Toast.makeText(this, "Thêm vào giỏ hàng lỗi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
         }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }
    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       String[] projection = {OrderContract.OrderEntry._ID,
               OrderContract.OrderEntry.COLUMN_NAME,
               OrderContract.OrderEntry.COLUMN_PRICE,
               OrderContract.OrderEntry.COLUMN_QUANTITY
       };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

            String nameofdrink = cursor.getString(name);
            //String priceofdrink = cursor.getString(price);
            String quantityofdrink = cursor.getString(quantity);

            drinnkName.setText(nameofdrink);
            coffeePrice.setText(String.format("%,d", price) + " VNĐ");
            quantitynumber.setText(quantityofdrink);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        drinnkName.setText("");
        coffeePrice.setText("");
        quantitynumber.setText("");
    }
}