package com.example.movieshowtimesearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

//implementation of the activity that represent the Result Page with the searched movie
public class ResultScreen extends AppCompatActivity {

    //result_screen elements
    private ImageView posterView;
    private TextView titleView;
    private TextView yearView;
    private TextView genreView;
    private TextView directorView;
    private TextView writerView;
    private TextView actorsView;
    private TextView ImdbView;
    private TextView RottenTomatoesView;
    private TextView MetacriticView;
    private ImageButton favouriteB;

    //container for the selection of favourites
    public static ArrayList<String> favMovies = new ArrayList<String>();
    public static String justTitle;

    //onCreate Funtion to create the Activity
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen);

        //finding the screen elements on the screen
        posterView = findViewById(R.id.poster);
        titleView = findViewById(R.id.title);
        yearView = findViewById(R.id.year);
        genreView = findViewById(R.id.genre);
        directorView = findViewById(R.id.director);
        writerView = findViewById(R.id.writer);
        actorsView = findViewById(R.id.actors);
        ImdbView = findViewById(R.id.imdbRating);
        RottenTomatoesView = findViewById(R.id.rottenTomatoes);
        MetacriticView = findViewById(R.id.metacritic);


        //get the data from the MainActivity
        Intent intent = getIntent();
        String posterText = intent.getStringExtra(MainActivity.POSTER_MESSAGE);
        justTitle = intent.getStringExtra(MainActivity.TITLE_MESSAGE);
        String yearText = intent.getStringExtra(MainActivity.YEAR_MESSAGE);
        String genreText = intent.getStringExtra(MainActivity.GENRE_MESSAGE);
        String directorText = intent.getStringExtra(MainActivity.DIRECTOR_MESSAGE);
        String writerText = intent.getStringExtra(MainActivity.WRITER_MESSAGE);
        String actorsText = intent.getStringExtra(MainActivity.ACTORS_MESSAGE);
        String imdbText = intent.getStringExtra(MainActivity.IMDB_MESSAGE);
        String rottentText = intent.getStringExtra(MainActivity.ROTTENTOMATOES_MESSAGE);
        String metacriticText = intent.getStringExtra(MainActivity.META_MESSAGE);


        //displaying the data on the result screen
        //Picasso library for displaying an image
        Picasso.get().load(posterText).into(posterView);
        titleView.setText(justTitle);
        yearView.setText("( "+ yearText + " )");
        genreView.setText("Genre: " + genreText);
        directorView.setText("Director: " + directorText);
        writerView.setText("Writer: " + writerText);
        actorsView.setText("Actors: " + actorsText);
        ImdbView.setText("Imdb Rating: " + imdbText);
        RottenTomatoesView.setText("Rotten Tomatoes Rating: " + rottentText);
        MetacriticView.setText("Metacritic Rating: " + metacriticText);

        //result_screen star for favourite selection
        favouriteB =  findViewById(R.id.favourite);
        //Add the selected movie as favourite to the favourites list
        favouriteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String favtitle = justTitle;
                //favourite movie added only once
                if(!favMovies.contains(favtitle))
                    favMovies.add(favtitle);
            }
        });

    }



}
