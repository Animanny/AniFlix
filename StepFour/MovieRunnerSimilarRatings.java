
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {
    int minimalRatings = 3;
    int year = 1990;
    int topNumberofSimUsers = 10;
    String raterID = "1034";
    String genre = "Drama";
    
    public void printAverageRatings(){
        FourthRatings frth = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+RaterDatabase.size()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
                
        ArrayList<Rating> movies = frth.getAverageRatings(minimalRatings);
        System.out.println("Found "+movies.size()+" movies with min raters.");
        
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+ MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings frth = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+RaterDatabase.size()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        AllFilters filt = new AllFilters();
        filt.addFilter(new YearAfterFilter(year));
        filt.addFilter(new GenreFilter(genre));
                
        ArrayList<Rating> movies = frth.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println(movies.size()+" movie(s) matched.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatings(){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings frth = new FourthRatings();
        ArrayList<Rating> movies = frth.getSimilarRatings("71",20, 5);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+ MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre (){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings frth = new FourthRatings();
        Filter filt = new GenreFilter("Mystery");
        ArrayList<Rating> movies = frth.getSimilarRatingsByFilter("964",20, 5,filt);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+ MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings frth = new FourthRatings();
        Filter filt = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> movies = frth.getSimilarRatingsByFilter("120",10, 2,filt);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+ MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings frth = new FourthRatings();
        AllFilters filt = new AllFilters();
        filt.addFilter(new GenreFilter("Drama"));
        filt.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> movies = frth.getSimilarRatingsByFilter("168",10,3, filt);
        
        for (Rating r : movies){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+MovieDatabase.getMinutes(r.getItem())+" minutes.");
            System.out.println(r.getValue());
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings frth = new FourthRatings();
        AllFilters filt = new AllFilters();
        filt.addFilter(new YearAfterFilter(1975));
        filt.addFilter(new MinutesFilter(70,200));
        ArrayList<Rating> movies = frth.getSimilarRatingsByFilter("314",10,5, filt);
        
        for (Rating r : movies){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getMinutes(r.getItem())+" minutes. "+r.getValue());
        }
    }
}
