package edu.android.and61_puzzlegame;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String IMAGE_ID = "image_id";

    private ImageView imageView;
    private int index;
    private TextView textName;

    private static final int[] IMAGE_LIST = {R.drawable.a5, R.drawable.b5};
    private static final String[] NAME_LIST = {"a5", "b5"}
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorDarkBlue)));
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorDarkBlue));

        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textName);

        imageView.setImageResource(IMAGE_LIST[index]);
        textName.setText(NAME_LIST[index]);

    } // end onCreate()

    public void startPuzzleGame(View view) {
        Intent intent = new Intent(this, OneActivity.class);
        intent.putExtra(IMAGE_ID, NAME_LIST[index]);
        startActivity(intent);
    } // end startPuzzleGameA()



    public void onClickNextImage(View view) {
        if(index < IMAGE_LIST.length-1) {
            index++;
        } else {
            index = 0;
        }
        imageView.setImageResource(IMAGE_LIST[index]);
        textName.setText(NAME_LIST[index]);
    } // end onClickNextImage()

    public void onClickPrevImage(View view) {
        if(index > 0) {
            index--;
        } else {
            index = (IMAGE_LIST.length -1);
        }
        imageView.setImageResource(IMAGE_LIST[index]);
        textName.setText(NAME_LIST[index]);

    } // end onClickPrevImage()



} // end class MainActivity
