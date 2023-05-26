package com.example.menusassignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    public static final String SETTINGS = "settings preferences";
    public static final String BOOKMARK = " ";
    View view;
    Context context;
    String[] list = new String[]{"World", "Tech", "Business", "Sports", "Entertainment"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings,container, false);
        ListView newsList = view.findViewById(R.id.settings_list);

        // ListView Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.fragment_settings,
                R.id.textView, list);
        newsList.setAdapter(adapter);

        registerForContextMenu(newsList);

        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    // Context menu listener
    public boolean onContextItemSelected(MenuItem item){
        //gets the menu info
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // Gets the position of the item clicked in the ListView
        String listItem = list[info.position];

        // Store the bookmark value clicked in the context menu
        SharedPreferences prefs = requireContext().getSharedPreferences(SETTINGS, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(BOOKMARK, listItem);
        editor.apply();

        // Display the toast confirmation
        if (item.getItemId() == R.id.bookmark){
            Toast.makeText(getActivity(), prefs.getString(BOOKMARK, "")
                    + " was added to favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Share selected", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}