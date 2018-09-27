
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {

    private double getAverageByID(String id, int minimalRaters){
        ArrayList<Double> movieRatings = new ArrayList();

        for (Rater r : RaterDatabase.getRaters()){
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

    private double dotProduct(Rater me, Rater r){
        ArrayList<String> meIDs = me.getItemsRated();
        ArrayList<String> rIDs = r.getItemsRated();
        double product = 0;

        for (String id :meIDs){
            if(r.hasRating(id)){
                product += (((me.getRating(id)-5) *(r.getRating(id)-5)));
            }
        }
        return product;
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> raters = new ArrayList();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if (r != me){
                double product = dotProduct(me,r);
                if(product > 0){
                    raters.add(new Rating(r.getID(),product));
                }
            }
        }
        Collections.sort(raters,Collections.reverseOrder());
        return raters;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){

        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<Rating> ratings = new ArrayList();

        for(String m : MovieDatabase.filterBy(new TrueFilter())){
            double adjustedRating = 0;
            int counter = 0;
            for(int k =0; k < numSimilarRaters;k++){
                if(RaterDatabase.getRater(similar.get(k).getItem()).hasRating(m)){
                    adjustedRating += similar.get(k).getValue() * RaterDatabase.getRater(similar.get(k).getItem()).getRating(m);
                    counter++;
                }
            }
            if(counter >= minimalRaters){
                ratings.add(new Rating(m,(adjustedRating/counter)));
            }
        }
        Collections.sort(ratings,Collections.reverseOrder());
        return ratings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria){

        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<Rating> ratings = new ArrayList();

        for(String m : MovieDatabase.filterBy(filterCriteria)){
            double adjustedRating = 0;
            int counter = 0;
            for(int k =0; k < numSimilarRaters;k++){
                if(RaterDatabase.getRater(similar.get(k).getItem()).hasRating(m)){
                    adjustedRating += similar.get(k).getValue() * RaterDatabase.getRater(similar.get(k).getItem()).getRating(m);
                    counter++;
                }
            }
            if(counter >= minimalRaters){
                ratings.add(new Rating(m,(adjustedRating/counter)));
            }
        }
        Collections.sort(ratings,Collections.reverseOrder());
        return ratings;
    }
}