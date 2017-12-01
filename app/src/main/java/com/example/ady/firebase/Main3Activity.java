package com.example.ady.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    public static final String TAG = Main3Activity.class.getSimpleName() ;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private FirebaseUser user;
    private List<Movie> listMovie;
    private String movieName;
    private String movieYear;
    private String movieDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("movies");
        listMovie = new ArrayList<>();


        user = FirebaseAuth.getInstance().getCurrentUser();
       // Log.d(TAG, "getMovies: hererererre");
        myRef
                .child(user.getUid())
                .child("favoritemovie")
                .addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for (DataSnapshot post: dataSnapshot.getChildren() ) {


                            Movie movies = post.getValue(Movie.class);
                            movieName = movies.getName();
                            movieYear = movies.getYear();
                            movieDirector = movies.getDirector();
                            listMovie.add(new Movie(movieName,movieDirector,movieYear));
                            Log.d(TAG, "INSIDE Name: "+movies.getName()
                                    + "INSIDE Director: "+movies.getYear() +
                                    "INSIDE Year: "+ movies.getYear());
                            RecyclerView recyclerView = findViewById(R.id.recycleMainAcitivity);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(Main3Activity.this);
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                            Recycleadapter recycleadapter = new Recycleadapter(listMovie);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(itemAnimator);
                            recyclerView.setAdapter(recycleadapter);

                        }//trying.setText(value);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.d(TAG, "Failed to read value.", error.toException());
                    }
                });



    }
}
