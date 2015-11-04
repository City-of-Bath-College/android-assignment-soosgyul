package questionapp.gyula.gs.com.questionapp;

/**
 * Created by soosg on 25/10/2015.
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

