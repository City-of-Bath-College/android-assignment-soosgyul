package questionapp.gyula.gs.com.questionapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;  //declaring the toolbar
    //variables for the app
    private Button btnFalse;
    private Button btnTrue;
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

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        startGame();
        Paper.init(this);
    }

    //this holds the questions
    private void generateQuestions(){
        questions = new ArrayList<>();

        //the first option is the button on the left. if the boolean set to false then the second option is correct
        questions.add(new QuestionObject("Where was the picture taken?", true, R.drawable.cuba, "cuba", "singapore", "the picture has cars in it!"));
        questions.add(new QuestionObject("This city is in which country?", false, R.drawable.barcelona, "Hungary", "Spain", "The beaches of barcelona"));
        questions.add(new QuestionObject("The steak should never be served:", true, R.drawable.steak, "well done", "medium", "blu, rare, medium-rare, medium, medium-well. These are all acceptable."));
/*
        questions.add(new QuestionObject("Who is on the picture?", true, R.drawable.nationalanthem, "Sacha Baron Cohen", "Freddie Mercury", "the picture has cars in it!"));
        questions.add(new QuestionObject("If you pay, you can swim alone in china?", true, R.drawable.china, "It is true", "Twaddle", "the picture has cars in it!"));
        questions.add(new QuestionObject("How much liquid you can take to an airplane?", false, R.drawable.customs, "no more than 1l", "10x100ml", "the picture has cars in it!"));
        questions.add(new QuestionObject("This is...", false, R.drawable.deface, "£10", "illegal in the uk", "the picture has cars in it!"));
        questions.add(new QuestionObject("This should be...", true, R.drawable.flipflops, "illegal", "compulsory", "the picture has cars in it!"));
        questions.add(new QuestionObject("What do you need to bring to a netflix and chill session?", false, R.drawable.valentinesday, "popcorn", "durex", "the picture has cars in it!"));
        questions.add(new QuestionObject("What is on the picture?", false, R.drawable.pufferfish, "Baseball ball", "Pufferfish", "the picture has cars in it!"));
*/
    }
    //this puts the questions together. stops when there are no more questions
    private void setUpQuestions(){
        if (index == questions.size()){ //if no more questions, jumps to the endGame
            Log.d("QuestionApp", "ended all the questions");
            endGame();
        }else {
            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());
            btnTrue.setText(currentQuestion.getOption1());
            btnFalse.setText(currentQuestion.getOption2());
            index++;
        }
    }

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
        answerResultAlert(result);

    }

    //this will generate an alert with explanation. This will replace the toasts.
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

    private void endGame(){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
                String howGoodItWent;
                int howManyQuestions = questions.size();

                if (score == 0) {
                    howGoodItWent = "Well, you tried";
                } else if (score > 0 && score <= 5) {
                    howGoodItWent = "Thanks for participating";
                }else if (score > 5) {
                    howGoodItWent = " Congratulations";
                }else{
                    howGoodItWent = "Error";
                }

                builder.setTitle(howGoodItWent);
                final EditText input = new EditText(this);
                builder.setView(input);
        builder.setMessage("You scored " + score + " point(s) this round.\nPlease enter your name:" );
        builder.setCancelable(false); //if this is not set, the user may click outside the alert, cancelling it. no cheating this way
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        playerName = input.getText().toString();
                        //high score
                        if (playerName.length() == 0){ //when the player doesn't enter anything for name, he will be called anonymous
                            playerName = "Anonymous";
                        }
                        HighScoreObject highScore = new HighScoreObject(playerName, score, new Date().getTime());
                        //get user prefs
                        List<HighScoreObject> highScores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());

                        //add item
                        highScores.add(highScore);

                        //save again
                        Paper.book().write("high scores", highScores);

                        //return to the intro screen
                        finish();
                    }
                })
                .create();
        builder.show();
    }

    private void startGame(){

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        txtScore = (TextView) findViewById(R.id.txtScore);

        index = 0;
        score = 0;
        txtScore.setText("Score: " + score);
        //on click listeners
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
}
