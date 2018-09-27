
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    
    public ArrayList<String> getItemsToRate (){
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filt = new YearAfterFilter(2000);
        ArrayList<String> list = MovieDatabase.filterBy(filt);
        while(list.size() > 15){
            list.remove(list.size()-1);
        }
        return list;
    }
    
    public void printRecommendationsFor (String webRaterID){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings frth = new FourthRatings();
        frth.getSimilarRatings(webRaterID,10,5);
        
    }

}
