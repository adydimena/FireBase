package com.example.ady.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG ="MainActivity2" ;
    EditText movieName ;
    EditText movieYear ;
    EditText movieDirector ;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String moviename, movieyear, moviedirector;
    private FirebaseUser user;
    private TextView trying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);




        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("movies");


        user = FirebaseAuth.getInstance().getCurrentUser();
        movieName = findViewById(R.id.etmoviName);
        movieDirector = findViewById(R.id.etmovieDirector);
        movieYear = findViewById(R.id.etmoviYear);
        trying = findViewById(R.id.trying);
    }

    public void AddMovie(View view) {
        Log.d(TAG, "AddMovie: " + view.getId());


        moviename = movieName.getText().toString();
        movieyear = movieYear.getText().toString();
        moviedirector = movieDirector.getText().toString();

        Movie movie = new Movie(moviename,moviedirector,movieyear);


        myRef.child(user.getUid())
                .child("favoritemovie")
                .push().setValue(movie)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Main2Activity.this,"movie added", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                        Toast.makeText(Main2Activity.this,"movie added NOT ADDED", Toast.LENGTH_LONG).show();

                    }
                });



    }

    public void getMovies(View view) {
        Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
    }

}
