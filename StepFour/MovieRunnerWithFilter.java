
/**
 * Write a description of MovieRunnerWithFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerWithFilter {
    int minimalRatings = 3;
    int year = 1990;
    String genre = "Drama";
    int min = 90;
    int max = 180;
    String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
    
    public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
                
        ArrayList<Rating> movies = tr.getAverageRatings(minimalRatings);
        System.out.println("Found "+movies.size()+" movies with min raters.");
        
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+ MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearsAfter(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        YearAfterFilter filt = new YearAfterFilter(year);
                
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println("Found "+movies.size()+" movies.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        GenreFilter filt = new GenreFilter(genre);
                
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println("Found "+movies.size()+" movies.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        MinutesFilter filt = new MinutesFilter(min,max);
                
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println("Found "+movies.size()+" movies.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        DirectorsFilter filt = new DirectorsFilter(directors);
                
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println("Found "+movies.size()+" movies.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" Time: "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        AllFilters filt = new AllFilters();
        filt.addFilter(new YearAfterFilter(year));
        filt.addFilter(new GenreFilter(genre));
                
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println(movies.size()+" movie(s) matched.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters.");
        System.out.println("read data for "+MovieDatabase.size()+" movies.");
        AllFilters filt = new AllFilters();
        filt.addFilter(new MinutesFilter(min,max));
        filt.addFilter(new DirectorsFilter(directors));
                
        ArrayList<Rating> movies = tr.getAverageRatingsByFilter(minimalRatings,filt);
        
        System.out.println(movies.size()+" movie(s) matched.");
  
        Collections.sort(movies);
        
        for (Rating r : movies){
            System.out.println(r.getValue()+" Time:"+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
}
