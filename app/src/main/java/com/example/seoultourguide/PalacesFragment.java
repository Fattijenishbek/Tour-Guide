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
 * PalacesFragment is the Class that displays the Palaces Tab in the Categories Activity.
 */
public class PalacesFragment extends Fragment {

    public PalacesFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        // Get String Array Resources from the Strings File
        String[] palacesNames = getResources().getStringArray(R.array.palaces_names);
        String[] palacesAddresses = getResources().getStringArray(R.array.palaces_addresses);
        String[] palacesDescriptions = getResources().getStringArray(R.array.palaces_descriptions);
        String[] palacesHours = getResources().getStringArray(R.array.palaces_hours);
        String[] palacesPhones = getResources().getStringArray(R.array.palaces_phones);

        // Add Google Maps URIs
        String[] palacesMapUris = {
                "https://www.google.com/maps/dir//Palace+of+Parliament,+Strada+Izvor+2-4,+Bucure%C8%99ti/@44.4275035,26.0873506,15z/data=!4m16!1m6!3m5!1s0x0:0x2b1089f802abaddc!2sPalace+of+Parliament!8m2!3d44.4275035!4d26.0873506!4m8!1m0!1m5!1m1!1s0x40b1ff427bee28c1:0x2b1089f802abaddc!2m2!1d26.0873506!2d44.4275035!3e3",
                "https://www.google.com/maps/dir//Cotroceni+National+Museum,+Bulevardul+Geniului+1,+Bucure%C8%99ti/@44.4352431,26.0633577,15z/data=!4m16!1m6!3m5!1s0x0:0xeca7ba35fbc72d4d!2sCotroceni+National+Museum!8m2!3d44.4352431!4d26.0633577!4m8!1m0!1m5!1m1!1s0x40b201dbbe0b8639:0xeca7ba35fbc72d4d!2m2!1d26.0633577!2d44.4352431!3e3",
                "https://www.google.com/maps/dir//Cantacuzino+Palace,+Calea+Victoriei+141,+Bucure%C8%99ti+010071/@44.448696,26.0883367,15z/data=!4m17!1m7!3m6!1s0x0:0x3e94de9f067b7471!2sCantacuzino+Palace,+Calea+Victoriei+141,+Bucure%C8%99ti+010071!3b1!8m2!3d44.448696!4d26.0883367!4m8!1m0!1m5!1m1!1s0x40b1ff55ca55e137:0x3e94de9f067b7471!2m2!1d26.0883367!2d44.448696!3e3",
        };

        // Add Images Resource IDs
        int[] palacesImages = {
                R.drawable.changdeokgung_palace,
                R.drawable.changyeonggung_palace,
                R.drawable.deoksugung_palace,
        };

        // Initialize ArrayList of Landmarks
        final ArrayList<Landmark> palaces = new ArrayList<>();

        // Add All Landmarks to the ArrayList
        for (int i = 0; i < palacesNames.length; i++) {
            palaces.add(new Landmark(
                    palacesNames[i],
                    palacesDescriptions[i],
                    palacesAddresses[i],
                    palacesHours[i],
                    Utils.addPrefix(palacesPhones[i]),
                    palacesMapUris[i],
                    palacesImages[i]));
        }

        // Set Custom List View Adapter
        ListView listView = (ListView) rootView.findViewById(R.id.cat_items_list);
        LandmarkAdapter adapter = new LandmarkAdapter(getActivity(), palaces);
        listView.setAdapter(adapter);

        // Set Click Listeners for Each Item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get Landmark Object at Current Position
                Landmark palace = palaces.get(position);

                // Start Intent and Send Landmark Object to DetailActivity
                Utils.openDetailActivity(getContext(), palace);

            }
        });

        return rootView;
    }
}
