package com.raymondhong.quizapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.raymondhong.quizapp.R;
import com.raymondhong.quizapp.helpers.Question;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private Button trueBtn;
    private Button falseBtn;
    private Button nextBtn;
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
        nextBtn = (Button) findViewById(R.id.next_button);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextQuestion();
            }
        });
        currentQuesIndex = 0;
        questionList = new ArrayList<>();

        initializeQuestions();

        //Set initial question
        Question nQuestion = questionList.get(currentQuesIndex);
        questionText.setText(nQuestion.getTextResID());
    }

    private void setNextQuestion() {
        if (currentQuesIndex + 1 < questionList.size()) {
            currentQuesIndex++;
            Question nQuestion = questionList.get(currentQuesIndex);
            questionText.setText(nQuestion.getTextResID());
        }
    }

    private void checkAnswer(boolean answer) {
        if (answer == questionList.get(currentQuesIndex).isCorrect()) {
            Toast.makeText(QuestionActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(QuestionActivity.this, R.string.wrong_toast, Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeQuestions() {
        questionList.add(new Question(R.string.question_one, true));
        questionList.add(new Question(R.string.question_two, true));
        questionList.add(new Question(R.string.question_three, false));
        questionList.add(new Question(R.string.question_four, true));
        questionList.add(new Question(R.string.question_five, false));
        questionList.add(new Question(R.string.question_six, true));
    }
}
