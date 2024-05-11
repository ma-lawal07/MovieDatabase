package com.flickfinder.controller;

import java.sql.SQLException;
import java.util.List;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class PersonController {

	// to complete the must-have requirements you need to add the following methods:
	// getAllPeople
	// getPersonById
	// you will add further methods for the more advanced tasks; however, ensure your have completed 
	// the must have requirements before you start these.
	
	private final PersonDAO personDAO;
	
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	/**
	 * Retrieves a list of Person objects, with an optional limit parameter.
	 * 
	 * @param ctx the Context object
	 */
	
	public void getAllPeople(Context ctx) {
		int limit = 50;
		String limitStr = ctx.queryParam("limit");
		if(limitStr != null && !limitStr.isEmpty()) {
			limit = Integer.parseInt(limitStr);
		}
		try {
			List<Person> people = personDAO.getAllPeople(limit);
			ctx.json(people);
		}catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	/**
	 * Retrieves a Person object with the specified ID.
	 * 
	 * @param ctx the Context object
	 */
	public void getPersonById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves a list of Movie objects that a Person has starred in.
	 * 
	 * @param ctx the Context object
	 */
		
	public void getMoviesStarringPerson(Context ctx) {
	    int personId = Integer.parseInt(ctx.pathParam("id"));
	    try {
	        List<Movie> movies = personDAO.getMoviesStarringPerson(personId);
	        ctx.json(movies);
	    } catch (SQLException e) {
	        ctx.status(500);
	        ctx.result("Database error");
	        e.printStackTrace();
	    }
	
	}

}