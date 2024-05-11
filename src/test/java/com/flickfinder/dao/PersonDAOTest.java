package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * TODO: Implement this class
 */
class PersonDAOTest {
   
	private PersonDAO personDAO;
	
	Seeder seeder;
	/**
	 * Sets up the database connection and creates the tables.
	 * We are using an in-memory database for testing purposes.
	 * This gets passed to the Database class to get a connection to the database.
	 * As it's a singleton class, the entire application will use the same
	 * connection.
	 */
	
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		personDAO = new PersonDAO();

	}
	/**
	 * Tests the getAllPeople method.
	 * We expect to get a list of all people in the database.
	 * We have seeded the database with 50 people, so we expect to get 50 people back.
	 * At this point, we avoid checking the actual content of the list.
	 */
	
	@Test
	void testGetAllPeople() {
		try {
			List<Person> person = personDAO.getAllPeople(50);
			assertEquals(50, person.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests the getPersonById method.
	 * We expect to get the person with the specified id.
	 */
	
	@Test
	void testGetPersonById() {
		Person person;
		try {
			person = personDAO.getPersonById(1);
			assertEquals("Tim Robbins", person.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	/**
	 * Tests the getMovieById method with an invalid id. Null should be returned.
	 */
	
	@Test
	void testGetPersonByIdInvalidId() {
		// write an assertThrows for a SQLException
		SQLException exception = assertThrows(SQLException.class, () -> personDAO.getPersonById(1000));
      try {
			Person person = personDAO.getPersonById(1000);
			assertEquals(null, person);
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}

	}
	
	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}

}