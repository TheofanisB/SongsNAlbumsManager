
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Song implements Serializable{

    private String title; 
    private String artist; 
    private String duration; 
    private String albumname; 

    public Song(String title, String artist, String duration, String albumname) { // constructor
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }
 //accessors 
    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDuration() {
        return duration;
    }
   
    public String getAlbumname() {
        return albumname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    @Override
    public String toString() {
        return "Song{" + "title=" + title + ", artist=" + artist + ", duration=" + duration + '}';
    }
    
    
    
    
    
    
    
    
}
