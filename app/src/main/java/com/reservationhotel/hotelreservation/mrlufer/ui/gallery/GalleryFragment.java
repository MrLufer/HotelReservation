package com.reservationhotel.hotelreservation.mrlufer.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.reservationhotel.hotelreservation.mrlufer.R;

public class GalleryFragment extends Fragment {
    ListView listView;
    String[] elementos = {"Belmond Miraflores Park", "JW Marriot Hotel Lima", "Courtyard Miraflores", "The Westin Lima hotel", "Hotel Estelar", "Swissôtel Lima", "Hilton Lima Miraflores", "Country Club Lima", "Casa Andina Private"};


    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        return root;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        listView = (ListView) getView().findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,elementos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();

            }
        });
    }
}