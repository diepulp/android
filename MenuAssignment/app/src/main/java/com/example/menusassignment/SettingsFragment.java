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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.fragment_settings,
                R.id.textView, list);
        newsList.setAdapter(adapter);

        registerForContextMenu(newsList);

        return view;
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String listItem = list[info.position];

        SharedPreferences prefs = requireContext().getSharedPreferences(SETTINGS, 0);
        SharedPreferences.Editor editor =prefs.edit();
        editor.putString(BOOKMARK, listItem);
        editor.apply();

        if (item.getItemId() == R.id.bookmark){
            Toast.makeText(getActivity(), prefs.getString(BOOKMARK, "")
                    + " was added to favorite", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Share selected", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}