
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;
public class DirectorsFilter implements Filter{
    List<String> myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = Arrays.asList(directors.split(","));
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String s : myDirectors){
            if (MovieDatabase.getDirector(id).contains(s)){
                return true;
            }
        }
        return false;
    }
}
