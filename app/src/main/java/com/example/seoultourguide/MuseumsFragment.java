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
 * MuseumsFragment is the Class that displays the Museums Tab in the Categories Activity.
 */
public class MuseumsFragment extends Fragment {

    public MuseumsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get String Array Resources from the Strings File
        String[] museumsNames = getResources().getStringArray(R.array.museums_names);
        String[] museumsAddresses = getResources().getStringArray(R.array.museums_addresses);
        String[] museumsDescriptions = getResources().getStringArray(R.array.museums_descriptions);
        String[] museumsHours = getResources().getStringArray(R.array.museums_hours);
        String[] museumsPhones = getResources().getStringArray(R.array.museums_phones);

        // Add Google Maps URIs
        String[] museumsMapUris = {
                "https://www.google.com/maps/dir//The+National+Museum+of+Art+of+Romania,+Calea+Victoriei+49-53,+Bucure%C8%99ti+010063/@44.4393668,26.095874,15z/data=!4m16!1m6!3m5!1s0x0:0xdea7c86f153f2330!2sThe+National+Museum+of+Art+of+Romania!8m2!3d44.4393668!4d26.095874!4m8!1m0!1m5!1m1!1s0x40b1ff45a2c1486f:0xdea7c86f153f2330!2m2!1d26.095874!2d44.4393668!3e3",
                "https://www.google.com/maps/dir//Muzeul+Satului+Dimitrie+Gusti,+%C8%98oseaua+Pavel+Dimitrievici+Kiseleff,+sector+1+28-30,+Bucure%C8%99ti+011347/@44.4723586,26.0765852,15z/data=!4m16!1m6!3m5!1s0x0:0x82df7091a7a741a3!2sMuzeul+Satului+Dimitrie+Gusti!8m2!3d44.4723586!4d26.0765852!4m8!1m0!1m5!1m1!1s0x40b202037c613773:0x82df7091a7a741a3!2m2!1d26.0765852!2d44.4723586!3e3",
                "https://www.google.com/maps/dir//Muzeu+CFR,+Strada+G%C4%83rii+de+Nord,+Bucure%C8%99ti/@44.447968,26.0731395,15z/data=!4m16!1m6!3m5!1s0x0:0x376e0682d84587c1!2sMuzeu+CFR!8m2!3d44.447968!4d26.0731395!4m8!1m0!1m5!1m1!1s0x40b201fb1a0e7f69:0x376e0682d84587c1!2m2!1d26.0731395!2d44.447968!3e3"
        };

        // Add Images Resource IDs
        int[] museumsImages = {
                R.drawable.national_museum,
                R.drawable.seoul_folk_museum,
                R.drawable.war_memorial_of_korea,
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> museums = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < museumsNames.length; i++) {
            museums.add(new Landmark(
                    museumsNames[i],
                    museumsDescriptions[i],
                    museumsAddresses[i],
                    museumsHours[i],
                    Utils.addPrefix(museumsPhones[i]),
                    museumsMapUris[i],
                    museumsImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) rootView.findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), museums);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark museum = museums.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Utils.openDetailActivity(getContext(), museum);

            }
        });

        return rootView;
    }
}
