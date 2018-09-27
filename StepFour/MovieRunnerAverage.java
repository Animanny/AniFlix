
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerAverage {
    int minimalRatings = 12;
    
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        System.out.println(sr.getRaterSize());
        System.out.println(sr.getMovieSize());
        
        ArrayList<Rating> movies = sr.getAverageRatings(minimalRatings);
        
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+ sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
        String title = "Spring Breakers";
        double rating = 0;
        String id = sr.getID(title);        
        ArrayList<Rating> movies = sr.getAverageRatings(minimalRatings);
        for (Rating m : movies){
            if (m.getItem().equals(id)){
                rating = m.getValue();
            }
        }
        
        if (rating != 0){
            System.out.println(title + " has a "+ rating +" rating.");
        } else {
            System.out.println("No such movie was found");
        }
        
    }
}
