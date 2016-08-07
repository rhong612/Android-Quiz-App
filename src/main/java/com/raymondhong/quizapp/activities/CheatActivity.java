package com.raymondhong.quizapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raymondhong.quizapp.R;

public class CheatActivity extends AppCompatActivity {


    private static final String EXTRA_CHEAT_KEY = "com.raymondhong.quizapp.cheat";

    private Button answerBtn;
    private Button backBtn;
    private TextView text;
    private boolean cheated;
    private boolean isCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        text = (TextView) findViewById(R.id.warning_tview);

        cheated = false;
        isCorrect = getIntent().getBooleanExtra(EXTRA_CHEAT_KEY, false);

        answerBtn = (Button) findViewById(R.id.answer_button);
        backBtn = (Button) findViewById(R.id.back_button);

        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cheated = true;
                if (isCorrect) {
                    text.setText(R.string.true_text);
                }
                else {
                    text.setText(R.string.false_text);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent cheatAct = new Intent(packageContext, CheatActivity.class);
        cheatAct.putExtra(EXTRA_CHEAT_KEY, answerIsTrue);
        return cheatAct;
    }
}
