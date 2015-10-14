package questionapp.gyula.gs.com.questionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class IntroductionActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnAbout;
    private Button btnStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);




        btnPlay = (Button)findViewById(R.id.btnStartGame);
        btnStats = (Button)findViewById(R.id.btnStats);
        btnAbout = (Button)findViewById(R.id.btnAbout);

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


}
