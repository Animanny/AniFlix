
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsFile){
        FirstRatings fr = new FirstRatings();
        this.myRaters = fr.loadRaters(ratingsFile);
    }
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        ArrayList<Double> movieRatings = new ArrayList();
        
        for (Rater r : myRaters){
            if(r.hasRating(id)){
                movieRatings.add(r.getRating(id));
            }
        }
        
        if (movieRatings.size() >= minimalRaters){
            double sum = 0;
            for (Double current:movieRatings){
                sum += current;
            }
            return (sum/movieRatings.size());
        } else {
            return 0.0;
        }
    }   
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> listOfAverageRatedMovies = new ArrayList();
        for (String m : movies){
            if (getAverageByID(m,minimalRaters) > 0.0){
                listOfAverageRatedMovies.add(new Rating(m,getAverageByID(m,minimalRaters)));
            }
        }
        
        return listOfAverageRatedMovies;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> listOfAverageRatedMovies = new ArrayList();
        for (String m : movieIDs){
            if (getAverageByID(m,minimalRaters) > 0.0){
                listOfAverageRatedMovies.add(new Rating(m,getAverageByID(m,minimalRaters)));
            }
        }
        return listOfAverageRatedMovies;
    }
    
    
    
}