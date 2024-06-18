package com.printchris;


import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class SecondaryScreen extends Presentation {

    private JSONObject data;

    public SecondaryScreen(Context outerContext, Display display, JSONObject data) {
        super(outerContext, display);
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.differentdisplay_basket, null);
        setContentView(view);

        if (data != null) {
            try {
                TextView textName = view.findViewById(R.id.Display_item_Name);
                TextView textPrice = view.findViewById(R.id.Display_item_Price);
                TextView textDesc = view.findViewById(R.id.Display_item_Desc);
                String item = data.getString("Name");
                String price = data.getString("Price");
                String desc = data.getString("Desc");
                Log.d("item", "item: "+item);
                Log.d("price", "price: "+price);
                Log.d("desc", "desc: "+desc);

                textName.setText(item);
                textPrice.setText(price);
                textDesc.setText(desc);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}