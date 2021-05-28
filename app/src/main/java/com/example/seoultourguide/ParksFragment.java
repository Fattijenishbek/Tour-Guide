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
 * ParksFragment is the Class that displays the Parks Tab in the Categories Activity.
 */
public class ParksFragment extends Fragment {

    public ParksFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get String Array Resources from the Strings File
        String[] parksNames = getResources().getStringArray(R.array.parks_names);
        String[] parksAddresses = getResources().getStringArray(R.array.parks_addresses);
        String[] parksDescriptions = getResources().getStringArray(R.array.parks_descriptions);
        String[] parksHours = getResources().getStringArray(R.array.parks_hours);
        String[] parksPhones = getResources().getStringArray(R.array.parks_phones);

        // Add Google Maps URIs
        String[] parksMapUris = {
                "https://www.google.com/maps/dir/42.892185,74.6283312/lotte+world+amusement+park/@34.337989,64.795649,3z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x357ca5a7250efe81:0x433df2c1fec03b98!2m2!1d127.098167!2d37.5111158",
                "https://www.google.com/maps/dir//King+Michael+I+Park,+Bucure%C8%99ti/@44.4702015,26.0827527,15z/data=!4m16!1m6!3m5!1s0x0:0x57f60ea89ec15526!2sKing+Michael+I+Park!8m2!3d44.4702015!4d26.0827527!4m8!1m0!1m5!1m1!1s0x40b20213bb079091:0x57f60ea89ec15526!2m2!1d26.0827527!2d44.4702015!3e3",
                "https://www.google.com/maps/dir//Ci%C8%99migiu+Park,+Bulevardul+Regina+Elisabeta,+Bucure%C8%99ti+030167/@44.4369687,26.0909837,15z/data=!4m16!1m6!3m5!1s0x0:0x3e98ab275166423b!2sCi%C8%99migiu+Park!8m2!3d44.4369687!4d26.0909837!4m8!1m0!1m5!1m1!1s0x40b1ff438f7e6fcf:0x3e98ab275166423b!2m2!1d26.0909837!2d44.4369687!3e3",
        };

        // Add Images Resource IDs
        int[] parksImages = {
                R.drawable.lotte_world,
                R.drawable.everland_img,
                R.drawable.seoul_land,
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> parks = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < parksNames.length; i++) {
            parks.add(new Landmark(
                    parksNames[i],
                    parksDescriptions[i],
                    parksAddresses[i],
                    parksHours[i],
                    Utils.addPrefix(parksPhones[i]),
                    parksMapUris[i],
                    parksImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) rootView.findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), parks);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark park = parks.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Utils.openDetailActivity(getContext(), park);

            }
        });

        return rootView;
    }
}
