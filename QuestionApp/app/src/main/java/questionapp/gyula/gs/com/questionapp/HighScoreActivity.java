package questionapp.gyula.gs.com.questionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivity extends AppCompatActivity {

    private ListView listView;
    private List<HighScoreObject> highscores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        listView = (ListView)findViewById(R.id.lstHighScoreList);

        Paper.init(this);

        highscores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());

        Toast.makeText(this, "Number = " + highscores.size() , Toast.LENGTH_SHORT).show();
        HighScoreAdapter adapter = new HighScoreAdapter(highscores);
    listView.setAdapter(adapter);
}

    private class HighScoreAdapter extends ArrayAdapter<HighScoreObject> {
        public HighScoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.row_highscore, null);
            }

            //get the highscore object for the row we are looking at
            HighScoreObject highScore = highscores.get(position);
            Date date = new Date(highScore.getTimestamp());

            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy/MM/dd");

            TextView lblTitle = (TextView)convertView.findViewById(R.id.lblTitle);
            lblTitle.setText(highScore.getName() + " on " + fmtOut.format(date)+ "\nScored: " + highScore.getScore());

            return convertView;
        }//end getview
    }//end adapter class

    //with this i will try to reset the scores

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_card, menu);
        return true;
    }
}