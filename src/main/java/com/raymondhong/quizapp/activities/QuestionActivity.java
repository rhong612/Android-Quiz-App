package com.raymondhong.quizapp.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.raymondhong.quizapp.R;
import com.raymondhong.quizapp.helpers.Question;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private static final String INDEX_KEY = "index";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button trueBtn;
    private Button falseBtn;
    private Button cheatBtn;
    private ImageButton nextBtn;
    private ImageButton prevBtn;
    private TextView questionText;
    private ArrayList<Question> questionList;
    private int currentQuesIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        questionText = (TextView) findViewById(R.id.question_text);

        trueBtn = (Button) findViewById(R.id.true_button);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        falseBtn = (Button) findViewById(R.id.false_button);
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        nextBtn = (ImageButton) findViewById(R.id.next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextQuestion();
            }
        });

        prevBtn = (ImageButton) findViewById(R.id.previous_button);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreviousQuestion();
            }
        });

        if (savedInstanceState != null)
            currentQuesIndex = savedInstanceState.getInt(INDEX_KEY);
        else
            currentQuesIndex = 0;

        questionList = new ArrayList<>();
        initializeQuestions();


        cheatBtn = (Button) findViewById(R.id.cheat_button);
        cheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cheatAct = CheatActivity.newIntent(QuestionActivity.this, questionList.get(currentQuesIndex).isCorrect());
                startActivityForResult(cheatAct, REQUEST_CODE_CHEAT);
            }
        });

        //Set initial question
        Question nQuestion = questionList.get(currentQuesIndex);
        questionText.setText(nQuestion.getTextResID());

    }


    /**
     * Changes the question displayed to the next one in the question list
     */
    private void setNextQuestion() {
        if (currentQuesIndex + 1 < questionList.size()) {
            currentQuesIndex++;
            Question nQuestion = questionList.get(currentQuesIndex);
            questionText.setText(nQuestion.getTextResID());
        }
    }

    /**
     * Changes the question displayed to the previous one in the question list
     */
    private void setPreviousQuestion() {
        if (currentQuesIndex - 1 >= 0) {
            currentQuesIndex--;
            Question nQuestion = questionList.get(currentQuesIndex);
            questionText.setText(nQuestion.getTextResID());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX_KEY, currentQuesIndex);
    }

    /**
     * Pops out a Toast that says "Correct" if the user selected the correct answer to the question, "Wrong!" otherwise
     * @param answer the user's answer to the question
     */
    private void checkAnswer(boolean answer) {
        if (answer == questionList.get(currentQuesIndex).isCorrect()) {
            Toast.makeText(QuestionActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(QuestionActivity.this, R.string.wrong_toast, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Adds the questions to the question list
     */
    private void initializeQuestions() {
        questionList.add(new Question(R.string.question_one, true));
        questionList.add(new Question(R.string.question_two, true));
        questionList.add(new Question(R.string.question_three, false));
        questionList.add(new Question(R.string.question_four, true));
        questionList.add(new Question(R.string.question_five, false));
        questionList.add(new Question(R.string.question_six, true));
    }
}
