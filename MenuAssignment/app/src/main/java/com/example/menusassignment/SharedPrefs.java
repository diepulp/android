package com.example.menusassignment;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Utility class to save and retrieve data from shared preferences
 */
public class SharedPrefs {

    public static final String MY_PREFS = "my_prefs";
    public static final String TEXT_ONE = "text_one";
    public static final String TEXT_TWO = "text_two";
    private static Context context;

    public SharedPrefs(Context context){
        this.context = context;
    }

    /**
     * Saves user input in the SharedPreferences
     * @param inputStringOne string

     */
   public void saveData(String inputStringOne){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT_ONE, inputStringOne);
        editor.apply();
   }

   //retrieves a string set from the preferences
//   public Set<String> loadData(){
//       SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS,
//               Context.MODE_PRIVATE);
//       return sharedPreferences.getStringSet(MY_PREFS, new HashSet<>());
//   }


    /**
     * Retrieves key/value pairs of the data stored in the SharedPreferences
     * @return Map<String, String>
     */
    public Map<String, ?> loadData(){
           SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS,
           Context.MODE_PRIVATE);
        return sharedPreferences.getAll();
    }

    /**
     * Concatenates the strings stored in the SharedPreferences
     * @return String
     */
    public String getEditStringFromPrefs(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);
            String combineInputs = sharedPreferences.getString(TEXT_ONE, " ");

        return combineInputs;
    }
}
