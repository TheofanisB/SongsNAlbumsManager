
import java.io.Serializable;
import java.util.ArrayList;


public class Album implements Serializable{
       private String description; // descriptino
    private String photo; // file location of the photo 
    private String genre; // music genre
    private int year_of_release; 
    private ArrayList<Song> lista_tragoudion = new ArrayList<Song>(); //list that contains the songs 

    public Album(String description, String photo, String genre, int year_of_release) {//constructor
        this.description = description;
        this.photo = photo;
        this.genre = genre;
        this.year_of_release = year_of_release;
    }
//accessors
    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear_of_release() {
        return year_of_release;
    }

    public ArrayList<Song> getLista_tragoudion() {
        return lista_tragoudion;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear_of_release(int year_of_release) {
        this.year_of_release = year_of_release;
    }

    public void setLista_tragoudion(ArrayList<Song> lista_tragoudion) {
        this.lista_tragoudion = lista_tragoudion;
    }
//toString
    @Override
    public String toString() {
        return "Album{" + "description=" + description + ", photo=" + photo + ", genre=" + genre + ", year_of_release=" + year_of_release + ", lista_tragoudion=" + lista_tragoudion + '}';
    }
    
    
    
    
    
    
}
