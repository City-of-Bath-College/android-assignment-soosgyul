package questionapp.gyula.gs.com.questionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnAbout;
    private Button btnStats;
    private TextView txtHighScore;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        btnPlay = (Button)findViewById(R.id.btnStartGame);
        btnStats = (Button)findViewById(R.id.btnStats);
        btnAbout = (Button)findViewById(R.id.btnAbout);
        txtHighScore = (TextView)findViewById(R.id.txtHighScore);
        Paper.init(this);


        //get user prefs



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileCard.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        updateTheHighScoreOnMainScreen();
    }
    private void updateTheHighScoreOnMainScreen() {
        List<HighScoreObject> highScores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());

        int maxScore = 0;
        String playerName = new String();
        if (highScores.size() > 0){
            for( int i = 0; i < highScores.size(); i++){

                HighScoreObject h = highScores.get(i);
                if (h.getScore() > maxScore ){
                    maxScore = h.getScore();
                    playerName = h.getName();
                }
            }
            txtHighScore.setText("Current high score is held by\n" + playerName + "\nwith the score of \n"+ maxScore);
        }else{
            //have a message if there are no high scores saved at all
            txtHighScore.setText("There are no high scores stored in the database");
        }
        //this method will loop through all the highscores and will display the highest
        /*List<HighScoreObject> highScores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());

        int maxScore = 0;
        if (highScores.size() > 0){

            for( int i = 0; i < highScores.size(); i++){

                HighScoreObject h = highScores.get(i);
                if (h.getScore() > maxScore ){
                    maxScore = h.getScore();
                }
            }
            txtHighScore.setText("Current high score is: "+ maxScore);
        }else{
            //have a message if there are no high scores saved at all
            txtHighScore.setText("There are no high scores stored in the database");
        }*/
    }


}
