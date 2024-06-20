package com.printchris;


import android.app.Presentation;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondaryScreen extends Presentation {

    private JSONObject data;

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
        TextView _paymentType = findViewById(R.id.paymentType);
        TextView _change = findViewById(R.id.change);
        TextView _total = findViewById(R.id.total);
        if (data != null) {
            try {

                List<item> itemList = new ArrayList<>();
                JSONObject content = data.getJSONObject("content");
                JSONArray items = content.getJSONArray("item");
                double payment = content.getDouble("payment");
                double change = content.getDouble("change");
                double tax = content.getDouble("tax");
                double itemSubtotal = content.getDouble("itemSubtotal");
                double totalAmount = content.getDouble("totalAmount");
                String date = content.getString("date");
                String tranID = content.getString("tranID");
                String paymentType = content.getString("paymentType");
                Log.d("payment", "payment: "+payment);
                Log.d("change", "change: "+change);
                Log.d("tax", "tax: "+tax);
                Log.d("itemSubtotal", "itemSubtotal: "+itemSubtotal);
                Log.d("totalAmount", "totalAmount: "+totalAmount);
                Log.d("date", "date: "+date);
                Log.d("tranID", "tranID: "+tranID);
                Log.d("paymentType", "paymentType: "+paymentType);
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    String itemName = item.getString("title");
                    String itemDesc = item.getString("Desc"); // Retrieve description field
                    String itemPrice = item.getString("Price");
                    String quantity = item.getString("quantity");
                    Log.d("item", "itemName: "+itemName);
                    Log.d("itemDesc", "itemDesc: "+itemDesc);
                    Log.d("itemPrice", "itemPrice: "+itemPrice);
                    Log.d("quantity", "quantity: "+quantity);
                    item _item = new item(itemName, itemDesc,itemPrice,quantity); // Create an Item object
                    itemList.add(_item);
                }
                _payment.setText((int) payment);
                _change.setText((int)change);
                _total.setText((int)totalAmount);
                _paymentType.setText(paymentType);
                CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getContext().getApplicationContext(),itemList);
                listView.setAdapter((customBaseAdapter));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}