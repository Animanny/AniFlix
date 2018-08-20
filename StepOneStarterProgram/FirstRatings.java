/**
 * Write a description of FirstRatings here.
 * 
 * @author Ani Srikanth
 * @version Jun 13, 2018
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        
        for(CSVRecord record:fr.getCSVParser()){
            movies.add(new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes"))));
        }
        return movies;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        int numberOfMovies = movies.size();
        HashMap<String,Integer> map = new HashMap<>();
        int comedy = 0;
        int longboi = 0;
        System.out.println("There are "+numberOfMovies+" movies");
        for (int i = 0; i < numberOfMovies;i++){
            if(movies.get(i).getGenres().contains("Comedy")){
                comedy++;
            } else {
            }
            
            if(movies.get(i).getMinutes() > 150){
                longboi++;
            }
            
        if (map.get(movies.get(i).getDirector()) == null){
            map.put(movies.get(i).getDirector(),1);
        } else {
            map.put(movies.get(i).getDirector(),map.get(movies.get(i).getDirector())+1);  
        }        
        
    }
    int max = 0;
    ArrayList <String> directors = new ArrayList();
    for (String s : map.keySet()){
        if (map.get(s)> max){
            directors.clear();
            max = map.get(s);
            directors.add(s);
        } else if(map.get(s) == max){
            directors.add(s);
        }
    }
    System.out.println("The max amount of movies by a director are "+max+". These are the directors:");
    for (String string:directors){
        System.out.println(string);
    }
    
    System.out.println("Comedy: "+comedy);
    System.out.println("Over 150 minutes: "+longboi);
    
    
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        ArrayList<Rater> raters = new ArrayList();
        for(CSVRecord rec:fr.getCSVParser()){
            boolean set = false;
            for(Rater r : raters){
                if (r.getID().equals(rec.get("rater_id"))){
                    r.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                    set = true;
                }  
            }
            if (!set){
                Rater toAdd = new EfficientRater(rec.get("rater_id"));
                toAdd.addRating(rec.get("movie_id"),Double.parseDouble(rec.get("rating")));
                raters.add(toAdd);
            }
        }
        return raters;
    }
    
    
    public void testLoadRaters(){
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        
        //-------How many raters, ratings by rater ---------//
        System.out.println("There are "+raters.size()+" raters.");       
        /*for(Rater r : raters){
            System.out.println(r.getID()+" has done "+r.numRatings()+" ratings.");
            for(int i = 0; i < r.numRatings();i++){
                System.out.println(r.getItemsRated().get(i)+" "+r.getRating(r.getItemsRated().get(i)));
            }
        }*/
        //------How many rates; specific rater--------------//
        String id = "193";
        boolean found = false;
        for(Rater r : raters){
            if (r.getID().equals(id)){
                System.out.println("The rater "+id+" has "+r.numRatings()+" ratings.");
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("No rater with that ID was found :(");
        }
        
        //---------Max Ratings; all raters with that amount of rates----//
        int max = 0;
        ArrayList <Rater> raterswmax = new ArrayList();
        for(Rater r : raters){
            if (r.numRatings()>max){
                raterswmax.clear();
                max = r.numRatings();
                raterswmax.add(r);
            } else if (r.numRatings() == max) {
                raterswmax.add(r);
            }
        }
        System.out.println("The max ratings by a rater is "+max+". The following rater(s) did this:");        
        for (Rater r : raterswmax){
            System.out.println(r.getID());
        }
        
        //=-=-=-=-=-=-MAX Ratings by Movie=-=-=-=-=-=-=-
        String movie_id = "1798709";
        int counter = 0;
        for (Rater r:raters){
            if (r.hasRating(movie_id)){
                counter++;
            }
        }   
        System.out.println("This movie has been rated "+counter+" times.");
        
        //-----------------Number of Movies Total-----------------------
        ArrayList<String> movies = new ArrayList();
        for (Rater r: raters){
            for (String currentMovie: r.getItemsRated()){
                if (!movies.contains(currentMovie)){
                    movies.add(currentMovie);
                }
            }
        }
        System.out.println("A total of "+movies.size()+" movies have been reviewed.");
    }
}


 
