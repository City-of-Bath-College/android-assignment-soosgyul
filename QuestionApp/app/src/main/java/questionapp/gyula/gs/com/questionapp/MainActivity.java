package questionapp.gyula.gs.com.questionapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        startGame();
        Paper.init(this);
    }

    //this holds the questions
    private void generateQuestions(){
        questions = new ArrayList<>();

        //the first option is the button on the left. if the boolean set to false then the second option is correct
        questions.add(new QuestionObject("Where was the picture taken?", true, R.drawable.cuba, "cuba", "singapore" ));
        questions.add(new QuestionObject("This city is in which country?", false, R.drawable.barcelona,"Hungary", "Spain"));
        questions.add(new QuestionObject("The steak should never be served:", true, R.drawable.steak, "well done", "medium"));
        questions.add(new QuestionObject("Who is on the picture?", true, R.drawable.nationalanthem, "Sacha Baron Cohen", "Freddie Mercury"));
        questions.add(new QuestionObject("If you pay, you can swim alone in china?", true, R.drawable.china, "It is true", "Twaddle"));
        questions.add(new QuestionObject("How much liquid you can take to an airplane?", false, R.drawable.customs, "no more than 1l", "10x100ml"));
        questions.add(new QuestionObject("This is...", false, R.drawable.deface, "£10", "illegal in the uk"));
        questions.add(new QuestionObject("This should be...", true, R.drawable.flipflops, "illegal", "compulsory"));
        questions.add(new QuestionObject("What do you need to bring to a netflix and chill session?", false, R.drawable.valentinesday, "popcorn", "durex"));
        questions.add(new QuestionObject("What is on the picture?", false, R.drawable.pufferfish, "Baseball ball", "Pufferfish"));
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

        if (answer == expectedAnswer){
            toasting("Correct");
            score++;
            txtScore.setText("Score: " + score);
        } else {
            toasting("False");
        }

        setUpQuestions();
    }

    private void toasting(String theMessage) {
        Toast.makeText(MainActivity.this, theMessage, Toast.LENGTH_SHORT).show();
    }

    private void endGame(){
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations")
                .setMessage("You scored " + score + " points this round.")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //high score
                HighScoreObject highScore = new HighScoreObject("Gyula", score, new Date().getTime());
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
        alertDialog.show();
        //i'll start the game again here, so there is no cheating
        startGame();
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
