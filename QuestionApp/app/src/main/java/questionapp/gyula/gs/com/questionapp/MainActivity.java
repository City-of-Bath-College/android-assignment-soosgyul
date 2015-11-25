package questionapp.gyula.gs.com.questionapp;
//imports for the app

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";
    private static final String KEY_SCORE = "score";
    private Toolbar toolbar;  //declaring the toolbar
    //variables for the app
    private Button btnLeft;
    private Button btnRight;
    private TextView lblQuestion;
    private TextView txtScore;
    private ImageView imgPicture;

    private String playerName = "";

    private List<QuestionObject> questions;
    private QuestionObject currentQuestion;
    private int index;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            index = savedInstanceState.getInt(KEY_INDEX, 0)-1;
            score = savedInstanceState.getInt(KEY_SCORE, 0);
        }else{
            index = 0;
            score = 0;
        }
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        questions = QuestionUtils.generalKnowledgeQuestions(); //setting up the array of questions from the QuestionUtils class
        startGame(); //calling the start game function
        Paper.init(this);
    }

    //this holds the questions in an arraylist
   private void generalKnowledgeQuestions(){
       QuestionUtils.generalKnowledgeQuestions(); //all the questions now are stored int the QuestionUtils class
    }

    //this puts the questions together on the activity. stops when there are no more questions
    private void setUpQuestions(){
        if (index == questions.size()){ //if no more questions, jumps to the endGame
            endGame();
        }else {
            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());
            btnRight.setText(currentQuestion.getOption1());
            btnLeft.setText(currentQuestion.getOption2());
            index++;
        }
    }

    //this function will test if the player answer is correct. if correct will increase the score by one
    private void determineButtonPress(boolean answer){
        boolean expectedAnswer = currentQuestion.isAnswer();
        String result;
        if (answer == expectedAnswer){
            result="Correct";
            score++;
            txtScore.setText("Score: " + score);
        } else {
            result="False";
        }
        //call this function to display the result to the player
        answerResultAlert(result);

    }

    //this will generate an alert with explanation. This replaces the toasts.
    private void answerResultAlert(String feedback){
        AlertDialog.Builder showResult = new AlertDialog.Builder(this);

        showResult.setTitle(feedback);
        showResult.setMessage(currentQuestion.getExplanation());
        showResult.setCancelable(false); //if this is not set, the user may click outside the alert, cancelling it. no cheating this way
        showResult.setNeutralButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //only when the ok is clicked will continue to run the program
                setUpQuestions();
            }
        });
        showResult.create();
        showResult.show();
    }

    //this is the endgame, which will generate a results alert with the score, and will prompt for the players name
    private void endGame(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        String howGoodItWent;
        int howManyQuestions = questions.size();
        //with the percentage calculations, the app is able to display a different finishing message based on the percentage of the allquestions/correct answers
        float percentage = 0;
        //test if there are any questions. this will prevent dividing with 0
        if (howManyQuestions != 0){
            percentage = (100*score)/howManyQuestions;
        }
        //different title message on 0 correct answers, between 1-50, and 51-100
        if (score == 0) {
            howGoodItWent = "Well, you tried";
        } else if (percentage > 0 && percentage <= 50) {
            howGoodItWent = "Thanks for participating" ;
        }else if (percentage > 50 && percentage <= 99) {
            howGoodItWent = "Congratulations";
        }else if (percentage ==100) {
            howGoodItWent = "Perfect Score!";
        }else{
            howGoodItWent = "Error";
        }

        builder.setTitle(howGoodItWent);//displaying the message set earlier based on score
        final EditText input = new EditText(this);
        builder.setView(input);
        builder.setMessage("You scored " + score + " point(s) this round.\nPlease enter your name:");
        builder.setCancelable(false); //if this is not set, the user may click outside the alert, cancelling it. no cheating this way
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playerName = input.getText().toString();
                //remove empty spaces
                playerName = playerName.trim();
                if (playerName.length() == 0){ //when the player doesn't enter anything for name, he will be called anonymous
                    playerName = "Anonymous";
                }
                HighScoreObject highScore = new HighScoreObject(playerName, score, new Date().getTime());
                //get user prefs
                List<HighScoreObject> highScores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());

                //add item
                highScores.add(highScore);

                //save into paper
                Paper.book().write("high scores", highScores);

                //return to the intro screen
                finish();
                }
        })
        .create();
        builder.show();
    }

    private void startGame(){
        //the start game process extracted from the onCreate - just to organise the code better
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        txtScore = (TextView) findViewById(R.id.txtScore);

        txtScore.setText("Score: " + score);
        //on click listeners - detecting the buttonpresses
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });
        generalKnowledgeQuestions();
        setUpQuestions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, index);
        outState.putInt(KEY_SCORE, score);
    }
}
