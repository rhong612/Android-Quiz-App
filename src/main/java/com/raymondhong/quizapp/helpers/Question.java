package com.raymondhong.quizapp.helpers;

/**
 * Created by Raymond on 7/31/2016.
 */
public class Question {
    private int textResID;
    private boolean isCorrect;

    public Question(int textResID, boolean isCorrect) {
        this.textResID = textResID;
        this.isCorrect = isCorrect;
    }
}
