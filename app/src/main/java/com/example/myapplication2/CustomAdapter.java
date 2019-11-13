package com.example.myapplication2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<Listing> listingList;
    private Context context;

    public CustomAdapter(ArrayList<Listing> list, Context cont){
        this.listingList = list;
        this.context = cont;
    }

    @Override
    public int getCount() {
        return this.listingList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listingList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.listing_layout, null);

            holder = new ViewHolder();
            holder.id = (TextView)convertView.findViewById(R.id.listingID);
            holder.address = (TextView)convertView.findViewById(R.id.listingAddress);
            holder.price = (TextView)convertView.findViewById(R.id.listingPrice);
            holder.image = (ImageView)convertView.findViewById(R.id.listingImage);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Listing stu = listingList.get(position);
        holder.id.setText(String.valueOf(stu.getId()));
        holder.address.setText(stu.getAddress());
        holder.price.setText(stu.getPrice());
        holder.image.setImageBitmap(BitmapFactory.decodeByteArray(stu.getImage(), 0, stu.getImage().length));


        return convertView;
    }

    private static class ViewHolder{
        public TextView id;
        public TextView address;
        public TextView price;
        public ImageView image;

    }



}