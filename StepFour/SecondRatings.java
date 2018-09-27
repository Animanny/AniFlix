
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviesFile, String ratingsFile){
        FirstRatings fr = new FirstRatings();
        this.myMovies = fr.loadMovies(moviesFile);
        this.myRaters = fr.loadRaters(ratingsFile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        ArrayList<Rating> listOfAverageRatedMovies = new ArrayList();
        for (Movie m : myMovies){
            if (getAverageByID(m.getID(),minimalRaters) > 0.0){
                listOfAverageRatedMovies.add(new Rating(m.getID(),getAverageByID(m.getID(),minimalRaters)));
            }
        }
        
        return listOfAverageRatedMovies;
    }
    
    public String getTitle(String id){
        String title = "No such ID was found";
        for(Movie m:myMovies){
                if(m.getID().equals(id)){
                     title = m.getTitle();
                }
        }
        return title;
    }
    
    public String getID(String title){
        String id = null;
        
        for(Movie m : myMovies){
            if (m.getTitle().equals(title)){
                id = m.getID();
            }
        }
        if(id != null){
            return id;
        } else {
            return "NO SUCH TITLE.";
        }
    }
}