package questionapp.gyula.gs.com.questionapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    private Toolbar toolbar;                              // Declaring the Toolbar Object

    private ListView listView;
    private List<HighScoreObject> highscores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        listView = (ListView)findViewById(R.id.lstHighScoreList);

        Paper.init(this); //database to store highscores

        highscores = Paper.book().read("high scores", new ArrayList<HighScoreObject>());


        HighScoreAdapter adapter = new HighScoreAdapter(highscores);
        listView.setAdapter(adapter);
    }

    private class HighScoreAdapter extends ArrayAdapter<HighScoreObject> {
        public HighScoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivity.this, 0, items);
        }

        @Override //to expand the layout to accept text
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


    @Override //creating and inflating menu to delete highscores
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_high_score, menu);
        return true;
    }

    @Override //when option selected, delete highscores
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selectionprivate void answerResultAlert(String feedback){
        //int id = item.getItemId();
        AlertDialog.Builder warning = new AlertDialog.Builder(this);

 //
           // when icon clicked
           // warning.setTitle("Erasing high score database");
            warning.setMessage("This action cannot be undone. Are you sure you want to delete the database?");
            warning.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //only when the ok is clicked will continue to run the program and delete
                    deleteDatabase();
                }
            });
            //if cancel is clicked, don't do anything
            warning.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            warning.create();
            warning.show();
        //}
        return super.onOptionsItemSelected(item);
    }
//function to delete the database , reload the screen and display a confirmation message
    public void deleteDatabase(){


        Paper.book().destroy();//delete the database
        super.recreate();
        Toast.makeText(this, "Database deleted", Toast.LENGTH_SHORT).show();
    }
}