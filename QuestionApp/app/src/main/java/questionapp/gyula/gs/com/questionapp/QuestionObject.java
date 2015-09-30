package questionapp.gyula.gs.com.questionapp;

/**
 * Created by soo14145300 on 30/09/2015.
 */
public class QuestionObject {

    private String question;
    private boolean answer;
    private int picture;

    public QuestionObject(String question, boolean answer, int picture) {
        this.question = question;

        this.answer = answer;
        this.picture = picture;
    }

    public String getQuestion() {

        return question;
    }


    public boolean isAnswer() {

        return answer;
    }

    public int getPicture() {
        return picture;
    }
}
