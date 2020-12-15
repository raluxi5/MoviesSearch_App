package com.example.movieshowtimesearch;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


//implementation of the activity that represent the FavouritesMovies selection of the user
public class FavScreen extends AppCompatActivity {

    //container to display the favourite movie titles
    private ListView fav_list;

    //onCreate Funtion to create the Activity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fav_screen);

        //finding the container element on the fav_screen
        fav_list = findViewById(R.id.fav_list);

        //adapter for the ListView to display an ArrayList, favourite movies ArrayList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(ResultScreen.favMovies)
        ){
            //make changes to the ListView element
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                // Initialize a TextView for ListView each Item
                TextView tv = view.findViewById(android.R.id.text1);
                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                //Set text size
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                // Generate ListView Item using TextView
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        fav_list.setAdapter(adapter);


    }

}
