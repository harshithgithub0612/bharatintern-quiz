// src/main/java/com/example/myquizapp/MainActivity.java
package com.example.quiz;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answerRadioGroup;
    private Button submitButton;

    // Array to store quiz questions and correct answers
    private String[] questions = {"What is the capital of France?", "Which planet is known as the Red Planet?", "What is the largest mammal on Earth?"};
    private String[] correctAnswers = {"Paris", "Mars", "Blue Whale"};

    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answerRadioGroup = findViewById(R.id.answerRadioGroup);
        submitButton = findViewById(R.id.submitButton);

        displayQuestion();  // Display the first question

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();  // Check the selected answer when the submit button is clicked
            }
        });
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.length) {
            // Set the question text
            questionTextView.setText("Question " + (currentQuestionIndex + 1) + ": " + questions[currentQuestionIndex]);

            // Set the answer options
            RadioButton optionARadioButton = findViewById(R.id.optionARadioButton);
            RadioButton optionBRadioButton = findViewById(R.id.optionBRadioButton);
            RadioButton optionCRadioButton = findViewById(R.id.optionCRadioButton);

            optionARadioButton.setText("A. " + correctAnswers[currentQuestionIndex]);
            optionBRadioButton.setText("B. " + "Random Option");
            optionCRadioButton.setText("C. " + "Another Random Option");

            // Clear the selected answer
            answerRadioGroup.clearCheck();
        } else {
            // Display a message when all questions are answered
            Toast.makeText(MainActivity.this, "Quiz Completed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = answerRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString().substring(3).trim();

            if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
                // Display a message for correct answer
                Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                // Display a message for incorrect answer
                Toast.makeText(MainActivity.this, "Incorrect. The correct answer is " + correctAnswers[currentQuestionIndex], Toast.LENGTH_SHORT).show();
            }

            // Move to the next question
            currentQuestionIndex++;
            displayQuestion();
        } else {
            // Display a message when no answer is selected
            Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }
    }
}
