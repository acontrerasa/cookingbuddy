package com.mantis.cookingbuddy;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given Meal 
 */

@ParseClassName("Review")
public class Review extends ParseObject {

	public Review() {
		// A default constructor is required.
	}

	public String getContent() {
		return getString("Content");
	}

	public void setContent(String content) {
		put("Content", content);
	}
	
	public String getRating() {
		return getString("Rating");
	}

	public void setRating(String rating) {
		put("Rating", rating);
	}
	
	public ParseUser getAuthor() {
		return getParseUser("author");
	}

	public void setAuthor(ParseUser user) {
		put("author", user);
	}

}
