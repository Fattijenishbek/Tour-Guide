package com.example.seoultourguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * MonumentsFragment is the Class that displays the Monuments Tab in the Categories Activity.
 */
public class MonumentsFragment extends Fragment {

    public MonumentsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get String Array Resources from the Strings File
        String[] monumentsNames = getResources().getStringArray(R.array.monuments_names);
        String[] monumentsAddresses = getResources().getStringArray(R.array.monuments_addresses);
        String[] monumentsDescriptions = getResources().getStringArray(R.array.monuments_descriptions);
        String[] monumentsHours = getResources().getStringArray(R.array.monuments_hours);
        String[] monumentsPhones = getResources().getStringArray(R.array.monuments_phones);

        // Add Google Maps URIs
        String[] monumentsMapUris = {
                "https://www.google.com/maps/dir//The+Arch+Of+Triumph,+Pia%C8%9Ba+Arcul+de+Triumf,+Bucure%C8%99ti/@44.4671777,26.078116,15z/data=!4m16!1m6!3m5!1s0x0:0x57ae7c6e837bc61b!2sThe+Arch+Of+Triumph!8m2!3d44.4671777!4d26.078116!4m8!1m0!1m5!1m1!1s0x40b202172654ca11:0x57ae7c6e837bc61b!2m2!1d26.078116!2d44.4671777!3e3",
                "https://www.google.com/maps/dir//Rebirth+Memorial,+Pia%C8%9Ba+Revolu%C8%9Biei,+Bucure%C8%99ti+030167/@44.4388942,26.097453,15z/data=!4m16!1m6!3m5!1s0x0:0xd2ec9ba0c7456177!2sRebirth+Memorial!8m2!3d44.4388942!4d26.097453!4m8!1m0!1m5!1m1!1s0x40b1ff45dcb30619:0xd2ec9ba0c7456177!2m2!1d26.097453!2d44.4388942!3e3",
                "https://www.google.com/maps/dir//Nation's+Heroes+Memorial,+Strada+General+Candiano+Popescu+105,+Bucure%C8%99ti/@44.41127,26.0968789,15z/data=!4m16!1m6!3m5!1s0x0:0x3e7635bc09da2!2sNation's+Heroes+Memorial!8m2!3d44.41127!4d26.0968789!4m8!1m0!1m5!1m1!1s0x40b1ff07f2ee63ad:0x3e7635bc09da2!2m2!1d26.0968789!2d44.41127!3e3",
        };

        // Add Images Resource IDs
        int[] monumentsImages = {
                R.drawable.sejong_monument,
                R.drawable.gwanghwamun_gate,
                R.drawable.gangnam_monument,
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> monuments = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < monumentsNames.length; i++) {
            monuments.add(new Landmark(
                    monumentsNames[i],
                    monumentsDescriptions[i],
                    monumentsAddresses[i],
                    monumentsHours[i],
                    Utils.addPrefix(monumentsPhones[i]),
                    monumentsMapUris[i],
                    monumentsImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) rootView.findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), monuments);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark monument = monuments.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Utils.openDetailActivity(getContext(), monument);

            }
        });

        return rootView;
    }
}
