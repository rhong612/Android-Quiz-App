package com.raymondhong.quizapp.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.raymondhong.quizapp.R;

public class CheatActivity extends AppCompatActivity {


    private static final String EXTRA_CHEAT_KEY = "com.raymondhong.quizapp.cheat";
    private static final String EXTRA_HAS_CHEATED = "com.raymondhong.quizapp.hascheated";
    private static final String KEY_CHEATED = "hascheated";

    private Button answerBtn;
    private Button backBtn;
    private TextView text;
    private TextView buildText;
    private boolean isCorrect;
    private boolean hasCheated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        text = (TextView) findViewById(R.id.warning_tview);

        isCorrect = getIntent().getBooleanExtra(EXTRA_CHEAT_KEY, false);

        answerBtn = (Button) findViewById(R.id.answer_button);
        backBtn = (Button) findViewById(R.id.back_button);

        hasCheated = false;

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCorrect) {
                    text.setText(R.string.true_text);
                }
                else {
                    text.setText(R.string.false_text);
                }
                hasCheated = true;

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = answerBtn.getWidth() / 2;
                    int cy = answerBtn.getHeight() / 2;
                    float radius = answerBtn.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(answerBtn, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            answerBtn.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }
                else {
                    answerBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buildText = (TextView) findViewById(R.id.build_version_text);
        String buildVer = "API Level " + Build.VERSION.SDK_INT;
        buildText.setText(buildVer);
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra(EXTRA_HAS_CHEATED, hasCheated);
        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATED, hasCheated);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        hasCheated = savedInstanceState.getBoolean(KEY_CHEATED);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent cheatAct = new Intent(packageContext, CheatActivity.class);
        cheatAct.putExtra(EXTRA_CHEAT_KEY, answerIsTrue);
        return cheatAct;
    }

    public static boolean wasAnswerShown(Intent data) {
        return data.getBooleanExtra(EXTRA_HAS_CHEATED, false);
    }
}
