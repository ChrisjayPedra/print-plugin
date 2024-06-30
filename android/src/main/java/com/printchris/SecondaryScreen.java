package com.printchris;

import android.annotation.SuppressLint;
import android.app.Presentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondaryScreen extends Presentation {

    private JSONObject data;
    public String modifiedString;
    //  public long startTimeInMillis = 600000;
    public SecondaryScreen(Context outerContext, Display display, JSONObject data) {
        super(outerContext, display);
        this.data = data;
    }

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.differentdisplay_basket, null);
        setContentView(view);
        listView = (ListView) findViewById(R.id.CustomListView);
        TextView _payment = findViewById(R.id.payment);
        ImageView _imageView = findViewById(R.id.QRCode);
        LinearLayout _qrcodeLayout = findViewById(R.id._qrcodeLayout);
        TextView _timerTextView = findViewById(R.id.timer);
        if (data != null && data.length() != 0) {
            Log.d("onCreate", "onCreate: " + data);
            List<item> itemList = new ArrayList<>();
            JSONObject content = data.optJSONObject("content");

            if (content != null) {
                JSONArray items = content.optJSONArray("item");
                double payment = content.optDouble("payment", 0.0);
                double change = content.optDouble("change", 0.0);
                double tax = content.optDouble("tax", 0.0);
                double itemSubtotal = content.optDouble("itemSubtotal", 0.0);
                double totalAmount = content.optDouble("totalAmount", 0.0);
                int timer = content.optInt("timer", 0);
                // Extracting string values from JSON
                String date = content.optString("date", "N/A");
                String tranID = content.optString("tranID", "N/A");
                String paymentType = content.optString("paymentType", "N/A");
                String barcode = content.optString("barcode", "N/A");
                String paymentCode = content.optString("paymentCode", "");
                if (paymentCode.startsWith("data:image/png;base64,")){
                    modifiedString = paymentCode.substring("data:image/png;base64,".length());
                    byte[] decodedString = Base64.decode(modifiedString, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    // Set the Bitmap to the ImageView
                    _imageView.setImageBitmap(decodedByte);
                    _qrcodeLayout.setVisibility(View.VISIBLE);
                    // 10 minutes in milliseconds
                    CountDownTimer countDownTimer = new CountDownTimer(timer, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            // Update the timer text view every second
                            long minutes = (millisUntilFinished / 1000) / 60;
                            long seconds = (millisUntilFinished / 1000) % 60;
                            String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                            _timerTextView.setText(timeLeftFormatted);
                        }

                        @Override
                        public void onFinish() {
                            // Timer finished
                            _timerTextView.setText("00:00");
                        }
                    }.start();

                }else{
                    _qrcodeLayout.setVisibility(View.GONE);
                }
                // Logging extracted values
                Log.d("payment", "payment: " + payment);
                Log.d("change", "change: " + change);
                Log.d("tax", "tax: " + tax);
                Log.d("itemSubtotal", "itemSubtotal: " + itemSubtotal);
                Log.d("totalAmount", "totalAmount: " + totalAmount);
                Log.d("date", "date: " + date);
                Log.d("tranID", "tranID: " + tranID);
                Log.d("paymentType", "paymentType: " + paymentType);
                Log.d("barcode", "barcode: " + barcode);
                Log.d("paymentCode", "paymentCode: " + paymentCode);
                if (items != null) {
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject item = items.optJSONObject(i);
                        if (item != null) {
                            String itemName = item.optString("title", "Unknown Item");
                            String quantity = item.optString("quantity", "0");
                            String itemDesc = item.optString("desc", "No Description");
                            double itemPrice = item.optDouble("regular_price", 0.0);
                            String productCode = item.optString("product_code", "N/A");
                            String categoryId = item.optString("category_id", "N/A");

                            // Logging item details
                            Log.d("product_code", "product_code: " + productCode);
                            Log.d("category_id", "category_id: " + categoryId);
                            Log.d("item", "itemName: " + itemName);
                            Log.d("quantity", "quantity: " + quantity);
                            Log.d("itemPrice", "itemPrice: " + itemPrice);

                            // Create an Item object and add it to the list
                            item _item = new item(itemName, itemDesc, itemPrice, quantity);
                            itemList.add(_item);
                        }
                    }
                }

                // Set text values for TextViews
                _payment.setText(String.format("$%.2f", totalAmount));


            } else {
                // Handle case where content is null
                Log.d("onCreate", "Content JSONObject is null.");
                _payment.setText("$0.00");
            }

            CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getContext().getApplicationContext(), itemList);
            listView.setAdapter(customBaseAdapter);

        } else {
            // Handle case where data is null or empty
            Log.d("onCreate", "Data JSONObject is null or empty.");
            _payment.setText("$0.00");
        }

    }

}

