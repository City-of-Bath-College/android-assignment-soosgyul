package com.gyula.gs.com.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyula.gs.com.questionapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //variables go here
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;

    private List<QuestionObject> questions;
    private QuestionObject currentQuestion;
    private int index;
    //private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect variables to interface items
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        index = 0;

        //onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        generateQuestions();
        setUpQuestions();
    }

    private void generateQuestions(){
        questions = new ArrayList<>();
        questions.add(new QuestionObject("In Cuba, tourists use a different currency to local people.", true, R.drawable.cuba));
        questions.add(new QuestionObject("When travelling to the UK from a country outside of the EU you can bring in any meat product just as long as the item has been vacuum-packed.", false, R.drawable.customs));
        questions.add(new QuestionObject("In Colombia, by law all the radio stations and TV stations must play the National Anthem at 6:00 am and 6:00 pm every day.", true, R.drawable.nationalanthem));
        questions.add(new QuestionObject("In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.", true, R.drawable.steak));
        questions.add(new QuestionObject("Despite the risks it is permitted for anyone to catch a deadly fish known as Fugo (pufferfish) and sell.", false, R.drawable.pufferfish));
        questions.add(new QuestionObject("In China families are allowed to have THREE children if neither parent has siblings.", false, R.drawable.china));
        questions.add(new QuestionObject("In Portugal you can be fined if caught driving whilst wearing flip-flops or not wearing a shirt.", true, R.drawable.flipflops));
        questions.add(new QuestionObject("Valentine’s day is banned in in Saudi Arabia.", true, R.drawable.valentinesday));
        questions.add(new QuestionObject("In the UK it is fine to scribble on money.", false, R.drawable.deface));
        questions.add(new QuestionObject("In Barcelona you are free to wear a bikini, swimming trunks or go bare-chested on the streets.", false, R.drawable.barcelona));
    }

    private void setUpQuestions(){
        currentQuestion = questions.get(index);

        lblQuestion.setText(currentQuestion.getQuestion());
        imgPicture.setImageResource(currentQuestion.getPicture());

        index++;
    }

    private void determineButtonPress(boolean answer){
        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer){
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            //score++;
        }else{
            Toast.makeText(MainActivity.this, "False!", Toast.LENGTH_SHORT).show();
        }
        setUpQuestions();
    }
}