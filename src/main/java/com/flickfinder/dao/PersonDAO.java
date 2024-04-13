package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<Person> getAllPeople() throws SQLException {
		List<Person> person = new ArrayList<>();

		Statement statement = connection.createStatement();
		
		// I've set the limit to 10 for development purposes - you should do the same.
		ResultSet rs = statement.executeQuery("select * from people LIMIT 20");
		
		while (rs.next()) {
			person.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return person;
	}
	
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
	
	

}
