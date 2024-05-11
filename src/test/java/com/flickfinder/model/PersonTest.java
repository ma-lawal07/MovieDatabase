package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 
 * TODO: Implement this class
 * 
 */

class PersonTest {
	/**
	 * The person object to be tested.
	 */
  private Person person;
  /**
	 * Set up the person object before each test.
	 *
	 */
  
  public void setUp() {
	  person = new Person(1, "Jack", 1985);
  }
  /**
	 * Test the person object is created with the correct values.
	 */
  
  @Test
	public void testPersonCreated() {
		assertEquals(1, person.getId());
		assertEquals("Jack", person.getName());
		assertEquals(1985, person.getBirth());
	}
  /**
	 * Test the person object is created with the correct values.
	 */
  @Test
	public void testPSetters() {
		person.setId(2);
		person.setName("Jessica");
		person.setBirth(2003);
		assertEquals(2, person.getId());
		assertEquals("Jessica", person.getName());
		assertEquals(2003, person.getBirth());
	}

}