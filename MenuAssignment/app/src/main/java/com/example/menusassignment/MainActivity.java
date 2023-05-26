/**
 * Assignment: Menus/Navigation Drawer
 * Author: Vladimir Ivanov
 * Date: 5-25-2023
 */
package com.example.menusassignment;

import static com.example.menusassignment.SettingsFragment.BOOKMARK;
import static com.example.menusassignment.SettingsFragment.SETTINGS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;



import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate the instance variables
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        prefs = getSharedPreferences(SETTINGS, 0);
        String bookmark = prefs.getString(BOOKMARK, "");

        // Adds settings menu to the toolbar
        // onCreateOptionsMenu doesn't work <?>
        toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setTitle("News");

        // Designate a toolbar as an action bar in the activity
        setSupportActionBar(toolbar);

        // Populates the hamburger menu
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Event listener for the Navigation View
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_sport);
        }

        displayBookmarkedFragment(bookmark);

    }

    public void displayBookmarkedFragment(String bookmark){
        if ( bookmark.equals("World")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WorldFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_world);
            Toast.makeText(this, bookmark, Toast.LENGTH_SHORT).show();
        } else if (bookmark.equals("Tech")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TechFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
        } else if (bookmark.equals("Business")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BusinessFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
        }  else if (bookmark.equals("Sports")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SportFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
        } else if (bookmark.equals("Entertainment")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new EntertainmentFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
        }
    }

    // Context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // Initialize an Options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    // Listener for the options menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new SettingsFragment()).commit();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_sport) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SportFragment()).addToBackStack(null).commit();
        } else if (itemId == R.id.nav_world) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WorldFragment()).addToBackStack(null).commit();
        } else if (itemId == R.id.nav_tech) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TechFragment()).addToBackStack(null).commit();
        } else if (itemId == R.id.nav_business) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BusinessFragment()).addToBackStack(null).commit();
        } else if (itemId == R.id.nav_entertainment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new EntertainmentFragment()).addToBackStack(null).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}