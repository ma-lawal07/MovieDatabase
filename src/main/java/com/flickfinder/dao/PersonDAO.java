package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * TODO: Implement this class
 * 
 */
public class PersonDAO {

	// for the must have requirements, you will need to implement the following
	// methods:
	// - getAllPeople()
	// - getPersonById(int id)
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.
	
	/* 
	 * The connection to a database
	 */
	private final Connection connection;
	
	/*
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 */
	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}
	
	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @return a list of all movies in the database
	 * @throws SQLException if a database error occurs
	 */
	public List<Person> getAllPeople(int limit) throws SQLException {
		List<Person> person = new ArrayList<>();
		String limitStr = limit > 0 ? "LIMIT" + limit : "";

		try (Statement statement = connection.createStatement()) {
		
		// I've set the limit to 10 for development purposes - you should do the same.
		ResultSet rs = statement.executeQuery("select * from people LIMIT 20");
		
		while (rs.next()) {
			person.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		}
		return person;
	}
	
	/**
	 * Retrieves a Person object with the specified ID.
	 * 
	 * @param id the ID of the person
	 * @return a Person object, or null if no person with the specified ID exists
	 * @throws SQLException if a database error occurs
	 */
	
	public Person getPersonById(int id) throws SQLException {

		String statement = "select * from people where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
		}
		
		// return null if the id does not return a person.

		return null;

	}
	
	
	/**
	 * Retrieves a list of movies starring a specific person.
	 * 
	 * @param personId the ID of the person
	 * @return a list of movies starring the person
	 * @throws SQLException if a database error occurs
	 */



	public List<Movie> getMoviesStarringPerson(int personId) throws SQLException {
	    List<Movie> movies = new ArrayList<>();
	    String query = "SELECT m.id, m.title, m.year FROM movies m " +
	                    "JOIN stars s ON m.id = s.movie_id " +
	                    "WHERE s.person_id = ?;";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setInt(1, personId);
	    ResultSet resultSet = statement.executeQuery();
	    while (resultSet.next()) {
	        int movieId = resultSet.getInt("id");
	        String title = resultSet.getString("title");
	        int year = resultSet.getInt("year");
	        Movie movie = new Movie(movieId, title, year);
	        movies.add(movie);
	    }
	    return movies;
	}
}
