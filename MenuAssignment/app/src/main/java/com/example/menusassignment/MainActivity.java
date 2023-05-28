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


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.menusassignment.model.Constants;
import com.example.menusassignment.model.articles.Article;
import com.example.menusassignment.model.articles.Root;
import com.example.menusassignment.model.data.INewsService;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    SharedPreferences prefs;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.error);

        String json = "{\n" +
                "      \"source\": {\n" +
                "        \"id\": \"the-washington-post\",\n" +
                "        \"name\": \"The Washington Post\"\n" +
                "      },\n" +
                "      \"author\": \"Dan Rosenzweig-Ziff\",\n" +
                "      \"title\": \"Nearly any material can harvest energy out of thin air, scientists find - The Washington Post\",\n" +
                "      \"description\": \"The technology builds on research that showed it was possible to capture the energy in humidity. The latest discovery finds it's possible to do so with any material.\",\n" +
                "      \"url\": \"https://www.washingtonpost.com/science/2023/05/26/harvest-energy-thin-air/\",\n" +
                "      \"urlToImage\": \"https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/52YSPI46IESSBZL6CT43CHRITE.jpg&w=1440\",\n" +
                "      \"publishedAt\": \"2023-05-27T01:20:21Z\",\n" +
                "      \"content\": \"Comment on this story\\r\\nComment\\r\\nNearly any material can be used to turn the energy in air humidity into electricity, scientists found in a discovery that could lead to continuously producing clean en… [+3522 chars]\"\n" +
                "    }";

        Gson gson = new Gson();
//        Article article = gson.fromJson(json, Article.class);
        System.out.println("article");

        //Instantiate Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEWS_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Retrofit implements the interface method
        INewsService newsService = retrofit.create(INewsService.class);

        // Execute the network request using the Call object
        Call<Root> call = newsService.getArticles();

        // execute async on the background thread
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code: " + response.code());
                    return;
                }

                List<Article> articles = response.body().getArticles();

                for (Article article : articles ){
                    String content = "";
                    content += "ID: " + article.getTitle();

                    // append is used to prevent overwriting on the previous post
                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });




        // Initiate the instance variables
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        prefs = getSharedPreferences(SETTINGS, 0);

        // get the stored bookmark value from the preferences
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

        // Default Fragment
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_sport);
        }

        displayBookmarkedFragment(bookmark);

    }

    /**
     * Queries SharedPreferences and sets the fragment in the view
     * based on the value received
     * @param bookmark string retrieved from shared preferences
     */
    public void displayBookmarkedFragment(String bookmark){
        if ( bookmark.equals("World")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new WorldFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_world);
            Toast.makeText(this, bookmark + " was stored in the preferences", Toast.LENGTH_SHORT).show();
        } else if (bookmark.equals("Tech")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TechFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
            Toast.makeText(this, bookmark + " was stored in the preferences", Toast.LENGTH_SHORT).show();
        } else if (bookmark.equals("Business")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new BusinessFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
            Toast.makeText(this, bookmark + " was stored in the preferences", Toast.LENGTH_SHORT).show();
        }  else if (bookmark.equals("Sports")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SportFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
            Toast.makeText(this, bookmark + " was stored in the preferences", Toast.LENGTH_SHORT).show();
        } else if (bookmark.equals("Entertainment")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new EntertainmentFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_tech);
            Toast.makeText(this, bookmark + " was stored in the preferences", Toast.LENGTH_SHORT).show();
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

    // Drawer layout navigation listener
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

    // Custom back press functionality
    // Closes the drawer instead of exiting the application
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}