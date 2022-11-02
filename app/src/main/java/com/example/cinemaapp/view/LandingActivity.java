package com.example.cinemaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.controls.OnSwipeTouchListener;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        View view = (View)findViewById(R.id.landing_layout);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                openActivity();
            }
        });

        introAnimation();
    }

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        this.finish();
    }

    private void introAnimation() {
        TextView swipeText = (TextView)findViewById(R.id.swipeSuggestionText);
        swipeText.startAnimation(AnimationUtils.loadAnimation(LandingActivity.this, R.anim.right_to_left_swipe));
        swipeText.animate().alpha(1f).setDuration(200);
    }
}

