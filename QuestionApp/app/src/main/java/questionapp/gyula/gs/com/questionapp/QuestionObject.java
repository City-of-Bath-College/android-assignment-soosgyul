package questionapp.gyula.gs.com.questionapp;

/**
 * Created by soosg on 08/10/2015.
 */
public class QuestionObject {
    private String question;
    private String option1;
    private String option2;
    private boolean answer;
    private int picture;

    public QuestionObject(String question, boolean answer, int picture, String option1, String option2){
        this.question = question;
        this.answer = answer;
        this.picture = picture;
        this.option1 = option1;
        this.option2 = option2;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isAnswer(){
        return answer;
    }

    public int getPicture(){
        return picture;
    }

    public String getOption1(){
        return option1;
    }

    public String getOption2(){
        return option2;
    }
}
