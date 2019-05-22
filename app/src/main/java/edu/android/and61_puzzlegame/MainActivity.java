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
    public static final String GAME1 = "쿠키 3x3";
    public static final String GAME2 = "겨울왕국 3x3";
    public static final String GAME3 = "해바라기 3x3";
    public static final String GAME4 = "점심식사 4x4";
    public static final String GAME5 = "개선문 4x4";

    private ImageView imageView;
    private int index;
    private TextView textName;

    private static final int[] IMAGE_LIST = {R.drawable.game4_a5, R.drawable.game4_b5, R.drawable.game4_c5, R.drawable.game4_d5, R.drawable.game4_e5 };
    private static final String[] NAME_LIST = {GAME1, GAME2, GAME3, GAME4, GAME5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4_main);

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
