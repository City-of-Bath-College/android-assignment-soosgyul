package questionapp.gyula.gs.com.questionapp;

import java.util.ArrayList;

/**
 * Created by soosg on 19/11/2015.
 */
public abstract class QuestionUtils {

    public static ArrayList<QuestionObject> generalKnowledgeQuestions(){
        ArrayList<QuestionObject> questions = new ArrayList<>();
        //the first option is the button on the left. if the boolean set to false then the second option is correct
        questions.add(new QuestionObject("Where was the picture taken?", true, R.drawable.cuba, "cuba", "singapore", "the picture has cars in it!"));
        questions.add(new QuestionObject("This city is in which country?", false, R.drawable.barcelona, "Hungary", "Spain", "The beaches of barcelona"));
        questions.add(new QuestionObject("The steak should never be served:", true, R.drawable.steak, "well done", "medium", "blu, rare, medium-rare, medium, medium-well. These are all acceptable."));
        questions.add(new QuestionObject("Who is on the picture?", true, R.drawable.nationalanthem, "Sacha Baron Cohen", "Freddie Mercury", "the picture has cars in it!"));
        questions.add(new QuestionObject("If you pay, you can swim alone in china?", true, R.drawable.china, "It is true", "Twaddle", "the picture has cars in it!"));
        questions.add(new QuestionObject("How much liquid you can take to an airplane?", false, R.drawable.customs, "no more than 1l", "10x100ml", "the picture has cars in it!"));
        questions.add(new QuestionObject("This is...", false, R.drawable.deface, "£10", "illegal in the uk", "the picture has cars in it!"));
        questions.add(new QuestionObject("This should be...", true, R.drawable.flipflops, "illegal", "compulsory", "the picture has cars in it!"));
        questions.add(new QuestionObject("What do you need to bring to a netflix and chill session?", false, R.drawable.valentinesday, "popcorn", "durex", "the picture has cars in it!"));
        questions.add(new QuestionObject("What is on the picture?", false, R.drawable.pufferfish, "Baseball ball", "Pufferfish", "the picture has cars in it!"));

        return questions;
    }
}