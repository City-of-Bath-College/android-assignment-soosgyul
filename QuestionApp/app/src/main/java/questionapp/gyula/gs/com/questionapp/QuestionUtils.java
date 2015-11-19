package questionapp.gyula.gs.com.questionapp;

import java.util.ArrayList;

/**
 * Created by soosg on 19/11/2015.
 */
public abstract class QuestionUtils {

    public static ArrayList<QuestionObject> generalKnowledgeQuestions(){
        ArrayList<QuestionObject> questions = new ArrayList<>();
        //the first option is the button on the left. if the boolean set to false then the second option is correct
        questions.add(new QuestionObject("Where was the picture taken?", true, R.drawable.cuba, "cuba", "singapore", "The picture was taken in Havana, capital of Cuba!"));
        questions.add(new QuestionObject("This city is in which country?", false, R.drawable.barcelona, "Hungary", "Spain", "Barcelona is considered the best beach city in the world."));
        questions.add(new QuestionObject("The steak should never be served:", true, R.drawable.steak, "Well done", "medium", "blu, rare, medium-rare, medium, medium-well. These are all acceptable."));
        questions.add(new QuestionObject("Who is on the picture?", true, R.drawable.nationalanthem, "Sacha Baron Cohen", "Freddie Mercury", "Sacha Baron Cohen plays a Kazakh journalist touring America in “Borat.”"));
        questions.add(new QuestionObject("If you pay, you can swim alone in china?", true, R.drawable.china, "It is true", "False", "Shenzhen City’s beach park in south China it proved so popular, it had over 130,000 visitors on the first day!"));
        questions.add(new QuestionObject("How much liquid you can take to an airplane?", false, R.drawable.customs, "A 1l bottle of soda", "10x100ml", "Liquids in containers larger than 100ml generally can’t go through security even if the container is only part full."));
        questions.add(new QuestionObject("This is...", false, R.drawable.deface, "£5", "Illegal in the uk", "Defacing a note is subject to a penalty under the Currency and Bank Notes Act 1928"));
        questions.add(new QuestionObject("What do you need to bring to a netflix and chill session?", false, R.drawable.valentinesday, "popcorn", "durex", "It’s a phrase that means, roughly, “hooking up.”"));
        questions.add(new QuestionObject("What is on the picture?", false, R.drawable.pufferfish, "Baseball ball", "Pufferfish", "It is a pufferfish and you should know that it contains enough toxin to kill 30 adult men."));
        questions.add(new QuestionObject("This should be...", true, R.drawable.flipflops, "illegal", "compulsory", "What goes flip flop,flip flop,flip flop,flip flop?\nPaul the octopus in flip flops."));

        return questions;
    }
}