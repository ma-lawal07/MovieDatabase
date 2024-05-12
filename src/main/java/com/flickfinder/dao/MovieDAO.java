package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * The Data Access Object for the Movie table.
 * 
 * This class is responsible for getting data from the Movies table in the
 * database.
 * 
 */
public class MovieDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 * 
	 */
	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all movies in the database.
	 * 
	 * @return a list of all movies in the database
	 * @throws SQLException if a database error occurs
	 */

	public List<Movie> getAllMovies(int limit) throws SQLException {
		List<Movie> movies = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM movies LIMIT ?")) {
			statement.setInt(1, limit > 0 ? limit : Integer.MAX_VALUE);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
			}
		}

		return movies;
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param id the id of the movie
	 * @return the movie with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Movie getMovieById(int id) throws SQLException {

		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}

		// return null if the id does not return a movie.

		return null;

	}
	/*
	 * Return the list of people in a movie.
	 * 
	 * @param movieId is the id of the movie
	 * 
	 * @return the list of people in the movie
	 * 
	 * @throws SQLException if a database error occurs
	 * 
	 */

	public List<Person> getPeopleByMovieId(int movieId) throws SQLException {
		List<Person> people = new ArrayList<>();

		String statement = "SELECT p.* FROM people p " + "JOIN stars s ON p.id = s.person_id " + "WHERE s.movie_id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, movieId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}
	/*
	 * Return an array list of movie ratings
	 * 
	 * @throws SQLException if a a database error occurs
	 */

	public List<MovieRating> getRatingsByYear(int year, int limit, int votes) throws SQLException {
		List<MovieRating> movies = new ArrayList<>();
		String limitStr = limit > 0 ? " LIMIT " + limit : "";
		try (PreparedStatement ps = connection.prepareStatement(
				"SELECT m.id, m.title, r.rating, r.votes, m.year FROM movies m JOIN ratings r ON m.id = r.movie_id WHERE m.year = ? AND r.votes > ?"
						+ limitStr)) {
			ps.setInt(1, year);
			ps.setInt(2, votes);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				movies.add(new MovieRating(rs.getInt("id"), rs.getString("title"), rs.getInt("year"),
						rs.getDouble("rating"), rs.getInt("votes")));
			}
		}
		return movies;
	}

}
