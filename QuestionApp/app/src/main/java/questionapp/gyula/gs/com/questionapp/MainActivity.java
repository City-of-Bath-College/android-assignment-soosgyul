package questionapp.gyula.gs.com.questionapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //variables for the app
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private TextView txtScore;
    private ImageView imgPicture;

    private List<QuestionObject> questions;
    private QuestionObject currentQuestion;
    private int index;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();
    }

    private void generateQuestions(){
        questions = new ArrayList<>();

        questions.add(new QuestionObject("This is the first question?", true, R.drawable.cuba));
        questions.add(new QuestionObject("This is the second question?", true, R.drawable.cuba));
        questions.add(new QuestionObject("This is the third question?", true, R.drawable.cuba));
        questions.add(new QuestionObject("This is the fourth question?", true, R.drawable.cuba));
    }

    private void setUpQuestions(){
        if (index == questions.size()){
            Log.d("QuestionApp", "ended all the questions");
            endGame();
        }else {
            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());


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
                .setNeutralButton("restart", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                    }
                })
                .create();
        alertDialog.show();
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
