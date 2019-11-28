package com.reservationhotel.hotelreservation.mrlufer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import android.app.FragmentTransaction;

import java.util.ArrayList;


public class AdapterHotel extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Hotel> items;

    public AdapterHotel (Activity activity, ArrayList<Hotel> items) {
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

    public void addAll(ArrayList<Hotel> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
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
            v = inf.inflate(R.layout.item_hotel, null);
        }

        final Hotel dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getTitle());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getDescription());





        final Button btnreserva = v.findViewById(R.id.btnreserva);

        btnreserva.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Details.class);
                intent.putExtra("title", dir.getTitle());
                intent.putExtra("category", dir.getCategoryId());
                intent.putExtra("description", dir.getDescription());
                intent.putExtra("id", dir.getId());

                activity.startActivity(intent);
            }
        });

        /*ImageView imagen = (ImageView) v.findViewById(R.id.imageView);
        imagen.setImageDrawable(dir.getImage());
*/
        return v;
    }

}
