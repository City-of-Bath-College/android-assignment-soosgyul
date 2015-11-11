package questionapp.gyula.gs.com.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        List<HighScoreObject> highScores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());
        int numberOfScores = 0;
        numberOfScores = highScores.size();
        String theNumberOfScores = String.valueOf(numberOfScores);

        Toast.makeText(HighScoreActivity.this, theNumberOfScores, Toast.LENGTH_SHORT).show();
    }

}