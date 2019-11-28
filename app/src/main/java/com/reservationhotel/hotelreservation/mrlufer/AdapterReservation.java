package com.reservationhotel.hotelreservation.mrlufer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterReservation extends BaseAdapter {

    private Activity activity;
    private ArrayList<Reservation> items;

    public AdapterReservation(Activity activity, ArrayList<Reservation> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Reservation> category) {
        items.addAll(category);
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_reservation, null);
        }

        final Reservation dir = items.get(position);

        TextView title = v.findViewById(R.id.text_reservation_title);
        title.setText(dir.getHotelTitle());

        TextView date = v.findViewById(R.id.text_reservation_date);
        date.setText(dir.getDate());

        TextView price = v.findViewById(R.id.text_reservation_price);
        price.setText("S/ " + dir.getPrice());

        return v;
    }

}
