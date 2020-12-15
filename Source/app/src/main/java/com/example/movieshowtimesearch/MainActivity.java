package com.example.movieshowtimesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


//implementation of the activity that represent the Home Page with the search functionality
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables used to store the input from the user
    public static final String POSTER_MESSAGE = "poster";
    public static final String TITLE_MESSAGE = "title";
    public static final String YEAR_MESSAGE = "year";
    public static final String GENRE_MESSAGE = "genre";
    public static final String DIRECTOR_MESSAGE = "director";
    public static final String WRITER_MESSAGE = "writer";
    public static final String ACTORS_MESSAGE = "actors";
    public static final String IMDB_MESSAGE = "imdbRating";
    public static final String ROTTENTOMATOES_MESSAGE = "rottenTomatoes";
    public static final String META_MESSAGE = "metacritic";

    //activity_main screen elements
    private SearchView searchBar;
    private Button buttonGo;
    private Button favourites_main;

    //container for API request
    private RequestQueue requestQueue;

    //onCreate Funtion to create the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Volley library for requests
        requestQueue = Volley.newRequestQueue(this);

        //search button
        buttonGo =  findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(this);
        //search widget
        searchBar = findViewById(R.id.searchBar);
        //favourite button
        favourites_main = findViewById(R.id.favourites_main);

    }
    //change activity from main activity screen (on favourites button click) to the favourites screen
    public void favourites(View v){
        Intent intent  = new Intent(MainActivity.this, FavScreen.class);
        startActivity(intent);
    }

    //Function for the click of go button, to make API request, after go button was clicked
    @Override
    public void onClick(View view) {
        final CharSequence query = searchBar.getQuery();
        //API URL
        String URL = "https://www.omdbapi.com/?apikey=f5429829&t="+query;
        JsonRequest movieRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null, //tipul requestului GET pe URL
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("API_RESPONSE", response.toString());
                        processResult(response);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,
                                "Couldn't connect to the API! Please try again!",
                                Toast.LENGTH_LONG).show();
                        Log.e("API_ERROR", error.getMessage());
                    }

                });
        //request added to the request container
        requestQueue.add(movieRequest);
    }

    //Function to get the data from the API and to step to the ResultScreen Activity
    private void processResult(JSONObject apiResponse){

        try{
            //get data(Strings) from the API by their name
            String poster = apiResponse.getString("Poster");
            String title = apiResponse.getString("Title");
            String year = apiResponse.getString("Year");
            String genre = apiResponse.getString("Genre");
            String director = apiResponse.getString("Director");
            String writer = apiResponse.getString("Writer");
            String actors = apiResponse.getString("Actors");

            JSONArray ratings = apiResponse.getJSONArray("Ratings");
            JSONObject rating1 = ratings.getJSONObject(0);
            String imdbRating = rating1.getString("Value");
            JSONObject rating2 = ratings.getJSONObject(1);
            String rottenTomatoes = rating2.getString("Value");
            JSONObject rating3 = ratings.getJSONObject(2);
            String metacritic = rating3.getString("Value");


            //set the Intent to pass to the Result Screen Activity
            Intent result = new Intent(this,ResultScreen.class);
            //pass extra Strings with the Intent
            result.putExtra(POSTER_MESSAGE,poster);
            result.putExtra(TITLE_MESSAGE,title); //putEXTRA ATRIBUI DATE
            result.putExtra(YEAR_MESSAGE,year);
            result.putExtra(GENRE_MESSAGE, genre);
            result.putExtra(DIRECTOR_MESSAGE,director);
            result.putExtra(WRITER_MESSAGE,writer);
            result.putExtra(ACTORS_MESSAGE,actors);
            result.putExtra(IMDB_MESSAGE,imdbRating);
            result.putExtra(ROTTENTOMATOES_MESSAGE,rottenTomatoes);
            result.putExtra(META_MESSAGE,metacritic);
            //Start the next Activity (ResultScreen)
            startActivity(result);
        //Parsing exception
        }catch(JSONException e){
            Toast.makeText(MainActivity.this,
                    "Wrong movie name, please write the correct title!",
                    Toast.LENGTH_LONG).show();
            Log.e("PARSER_ERROR",e.getMessage());
        }
    }
}
