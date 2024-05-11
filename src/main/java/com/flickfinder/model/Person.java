package com.flickfinder.model;

/**
 * A person in the movie database.
 * 
 * @TODO: Implement this class
 */
public class Person {

	// - Add your code here: use the MovieDAO.java as an example
	// - Check the ERD and database schema in the docs folder
	// (./docs/database_schema.md) to ensure each column in the People table
	// has an attribute in the model. (DELETE THIS COMMENT WHEN DONE)

	private int id;
	private String name;
	private int birth;

	// Constructor to initialize the person with object id
	public Person(int id, String name, int birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	/**
	 * Returns the person's id.
	 *
	 * @return the person's id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * set the persons id.
	 *
	 * @return id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the person's name.
	 *
	 * @return the person's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the person's name.
	 *
	 * @return sets the person's name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the date of birth.
	 *
	 * @return the date of birth of person
	 */
	public int getBirth() {
		return birth;
	}

	/**
	 * sets the date of birth of the person
	 *
	 * @return date of birth
	 */
	public void setBirth(int birth) {
		this.birth = birth;
	}

	// ToString method
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}

}
