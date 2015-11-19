package questionapp.gyula.gs.com.questionapp;

/**
 * Created by soosg on 25/10/2015.
 * this is used to create highscore objects, and to be able to store these later in Paper
 */
public class HighScoreObject {
    private String name;
    private int highScore;
    private long timestamp;

    public HighScoreObject(String name, int score, long timestamp){
        this.name = name;
        this.highScore = score;
        this.timestamp = timestamp;
    }

    public HighScoreObject(){}

    public String getName(){return name;}
    public int getScore(){return highScore;}
    public long getTimestamp() {return timestamp;}
}

