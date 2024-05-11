package com.flickfinder.model;

public class MovieRating extends Movie {
	
	//The rating of a movie
	private double rating;
	
	//The number of votes casted for a particular movie
	private int votes;

	//Constructor for movie rating object
	public MovieRating(int id, String title, int year, double rating, int votes) {
		super(id, title, year);
        this.rating = rating;
        this.votes = votes;
	}

	//Returns the rating of the movie
	public double getRating() {
        return rating;
    }

	//Set the rating of this movie
    public void setRating(double rating) {
        this.rating = rating;
    }

    //Return the votes on this movie
    public int getVotes() {
        return votes;
    }

    //Sets the votes on this movie
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
