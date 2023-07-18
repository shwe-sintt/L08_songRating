package sg.edu.rp.c346.id22003619.l08_songrating;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars){
        this.id=id;
        this.title=title;
        this.singers=singers;
        this.year=year;
        this.stars=stars;

    }
    public int getId(){ return id;}

    public String getTitle(){ return title;}

    public String getSingers(){ return singers;}

    public int getYear(){ return year;}

    public int getStar(){return stars;}
    public String prettyStar(){
        String star="*".repeat(stars);
        return star;
    }
    public void setSongContent(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }


    @NonNull
    @Override
    public String toString() {
        return "Song title: "+ title + "\nSinger: " + singers + "\nYear Released: " + year + "\nStars: " + stars;
    }
}
