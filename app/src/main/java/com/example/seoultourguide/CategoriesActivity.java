package com.example.seoultourguide;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

/**
 * Class that initializes the Categories Activity for the app.
 */
public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Add Back (Up) Functionality
        Utils.activateBackBtn(CategoriesActivity.this, R.id.category_back_btn);

        // Initiate the ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.category_view_pager);
        CategoriesAdapter adapter = new CategoriesAdapter(getSupportFragmentManager(), CategoriesActivity.this);
        viewPager.setAdapter(adapter);

        // Set Tab Titles
        TabLayout tabLayout = (TabLayout) findViewById(R.id.category_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Select Tab from Intent
        TabLayout.Tab tab = tabLayout.getTabAt(getIntent().getIntExtra("selected_index", 0));
        if (tab != null) tab.select();
    }
}
