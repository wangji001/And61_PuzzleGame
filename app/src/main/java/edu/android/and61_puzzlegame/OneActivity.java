package edu.android.and61_puzzlegame;


import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.util.Collections;
import java.util.Vector;

import static edu.android.and61_puzzlegame.MainActivity.IMAGE_ID;

import edu.android.and61_puzzlegame.databinding.ActivityGame4OneBinding;
import static edu.android.and61_puzzlegame.MainActivity.GAME1;
import static edu.android.and61_puzzlegame.MainActivity.GAME2;
import static edu.android.and61_puzzlegame.MainActivity.GAME3;
import static edu.android.and61_puzzlegame.MainActivity.GAME4;
import static edu.android.and61_puzzlegame.MainActivity.GAME5;


public class OneActivity extends AppCompatActivity {

    ActivityGame4OneBinding binding;

    OneAdapter adapter;
    CheckAvailable checkAvailable;
    Vector<One> mOne;
    int[] imgs;

    private static int N ;

    private static int[] image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game4_one);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorDarkBlue)));
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorDarkBlue));

       String image_id = getIntent().getStringExtra(IMAGE_ID);

        if(image_id.equals(GAME1)) {
            N=3;
            image = new int[]{R.drawable.game4_a5_001, R.drawable.game4_a5_002, R.drawable.game4_a5_003, R.drawable.game4_a5_004,
                    R.drawable.game4_a5_005, R.drawable.game4_a5_006, R.drawable.game4_a5_007, R.drawable.game4_a5_008, R.drawable.game4_a5_009};


            binding.correctImageView.setImageResource(R.drawable.game4_a5);
        } else if (image_id.equals(GAME2)) {
            N = 3;
            image = new int[] {R.drawable.game4_b5_001, R.drawable.game4_b5_002, R.drawable.game4_b5_003, R.drawable.game4_b5_004,
                    R.drawable.game4_b5_005, R.drawable.game4_b5_006, R.drawable.game4_b5_007, R.drawable.game4_b5_008, R.drawable.game4_b5_009};


            binding.correctImageView.setImageResource(R.drawable.game4_b5);
        } else if(image_id.equals(GAME3)){

            N=3;
            image = new int[] {R.drawable.game4_c5_001, R.drawable.game4_c5_002, R.drawable.game4_c5_003, R.drawable.game4_c5_004, R.drawable.game4_c5_005,
                    R.drawable.game4_c5_006, R.drawable.game4_c5_007, R.drawable.game4_c5_008, R.drawable.game4_c5_009};

            binding.correctImageView.setImageResource(R.drawable.game4_c5);
        } else if(image_id.equals(GAME4)){
            N=4;
            image = new int[] {R.drawable.game4_d5_001, R.drawable.game4_d5_002, R.drawable.game4_d5_003, R.drawable.game4_d5_004, R.drawable.game4_d5_005, R.drawable.game4_d5_006,
                    R.drawable.game4_d5_007, R.drawable.game4_d5_008, R.drawable.game4_d5_009, R.drawable.game4_d5_010, R.drawable.game4_d5_011, R.drawable.game4_d5_012,
                    R.drawable.game4_d5_013, R.drawable.game4_d5_014, R.drawable.game4_d5_015, R.drawable.game4_d5_016,};
            binding.correctImageView.setImageResource(R.drawable.game4_d5);
        } else if (image_id.equals(GAME5)){
            N=4;
            image = new int[] {R.drawable.game4_e5_001, R.drawable.game4_e5_002, R.drawable.game4_e5_003, R.drawable.game4_e5_004, R.drawable.game4_e5_005,
                    R.drawable.game4_e5_006, R.drawable.game4_e5_007, R.drawable.game4_e5_008, R.drawable.game4_e5_009, R.drawable.game4_e5_010, R.drawable.game4_e5_011,
                    R.drawable.game4_e5_012, R.drawable.game4_e5_013, R.drawable.game4_e5_014, R.drawable.game4_e5_015, R.drawable.game4_e5_016,};
            binding.correctImageView.setImageResource(R.drawable.game4_e5);
        }


        // span과 이미지 갯수만 변경되면 모든 M x N 적용가능
        GridLayoutManager layoutManager = new GridLayoutManager(this, N);
        binding.oneViews.setLayoutManager(layoutManager);
        adapter = new OneAdapter(this);
        binding.oneViews.setAdapter(adapter);
        mOne = new Vector<>();

        int randInt = (int) (Math.random() * image.length);
        for (int i = 0; i < image.length; i++) {
            if (i == randInt) {
                mOne.add(new One(R.drawable.game4_image_white, i, true));
            } else {
                mOne.add(new One(image[i], i, false));
            }
        }



        // TODO 이것 대신 섞어주는 코드 필요
        // 아래의 코드대로 섞으면 맞출 수 있는 확률이 50%정도

        Collections.shuffle(mOne);
        adapter.update(mOne);

        binding.oneViews.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int length = binding.oneViews.getWidth() / N;
                adapter.setLength(length);
                adapter.notifyDataSetChanged();
                checkAvailable = new CheckAvailable(mOne, N);
                binding.oneViews.addOnItemTouchListener(itemTouchListener);
                binding.oneViews.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


        binding.btnRestart.setOnClickListener(view -> recreate());
    } // end onCreate()

    RecyclerView.OnItemTouchListener itemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView parent, @NonNull MotionEvent evt) {
            int action = evt.getAction();
            if (action == MotionEvent.ACTION_UP) {
                View child = parent.findChildViewUnder(evt.getX(), evt.getY());
                assert child != null;
                int pos = parent.getChildAdapterPosition(child);
                int newPos = checkAvailable.check(pos);
                if (newPos != -100) {
                    adapter.change(pos, newPos);
                }

                int good_job = 0;
                for (int i = 0; i < mOne.size(); i++) {
                    if (i == mOne.get(i).getTag()) {
                        good_job++;
                    }
                }

                if (good_job == mOne.size()) {
                    Vector<One> one = adapter.currentOne();
                    for (int i = 0; i < one.size(); i++) {
                        boolean empty = one.get(i).isEmpty();
                        if (empty) {
                            adapter.finish(i);
                            binding.oneViews.removeOnItemTouchListener(itemTouchListener);
                            break;
                        }
                    }
                    Toast.makeText(OneActivity.this, "참 잘했어요.", Toast.LENGTH_SHORT).show();
                } // end if
            } // end if
            return false;
        } // end onInterceptTouchEvent()

        @Override
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        } // end onTouchEvent()

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {

        }
    }; // itemTouchListener

} // end class OneActivity
