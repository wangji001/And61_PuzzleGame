package edu.android.and61_puzzlegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String IMAGE_ID = "image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    } // end onCreate()

    public void startPuzzleGameA(View view) {
        Intent intent = new Intent(this, OneActivity.class);
        intent.putExtra(IMAGE_ID, "a5");
        startActivity(intent);
    } // end startPuzzleGameA()

    public void startPuzzleGameB(View view) {
        Intent intent = new Intent(this, OneActivity.class);
        intent.putExtra(IMAGE_ID, "b5");
        startActivity(intent);
    } // end startPuzzleGameB()

} // end class MainActivity
