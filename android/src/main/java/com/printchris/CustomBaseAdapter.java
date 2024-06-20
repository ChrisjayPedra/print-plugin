package com.printchris;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {



    Context context;
    List<item> itemList;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, List<item> itemList) {
        this.context = ctx;
        this.itemList = itemList;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // Return the position as ID
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.activity_custome_list_view,null);
            holder = new ViewHolder();
            holder.textName = view.findViewById(R.id.Display_item_Name);
            holder.textPrice = view.findViewById(R.id.Display_item_Price);
            holder.textDesc = view.findViewById(R.id.Display_item_Desc);
            holder.textQuantity = view.findViewById(R.id.Display_item_quantity);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        item currentItem = itemList.get(position);

        holder.textName.setText(currentItem.getName());
        holder.textPrice.setText(currentItem.getPrice());
        holder.textDesc.setText(currentItem.getDescription());
        holder.textQuantity.setText(currentItem.getQuantity());
        return view;
    }

    static class ViewHolder {
        TextView textName;
        TextView textPrice;
        TextView textDesc;
        TextView textQuantity;
    }
}
