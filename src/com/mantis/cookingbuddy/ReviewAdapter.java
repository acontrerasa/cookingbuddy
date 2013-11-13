package com.mantis.cookingbuddy;

import java.util.Arrays;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.mantis.cookingbuddy.R;
/*
 * The FavoriteMealAdapter is an extension of ParseQueryAdapter
 * that has a custom layout for favorite meals, including a 
 * bigger preview image, the meal's rating, and a "favorite"
 * star. 
 */

public class ReviewAdapter extends ParseQueryAdapter<Review> {

	public ReviewAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<Review>() {
			public ParseQuery<Review> create() {
				// Here we can configure a ParseQuery to display
				// only top-rated meals.
				ParseQuery query = new ParseQuery("Recipe");
				query.orderByDescending("Rating");
				return query;
			}
		});
	}

	@Override
	public View getItemView(Review review, View v, ViewGroup parent) {

		if (v == null) {
			v = View.inflate(getContext(), R.layout.parse_review_item, null);
		}

		super.getItemView(review, v, parent);

		ParseImageView mealImage = (ParseImageView) v.findViewById(R.id.icon);
		ParseFile photoFile = review.getParseFile("photo");
		if (photoFile != null) {
			mealImage.setParseFile(photoFile);
			mealImage.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e) {
					// nothing to do
				}
			});
		}

		TextView titleTextView = (TextView) v.findViewById(R.id.text1);
		titleTextView.setText(review.getContent());
		TextView authorTextView = (TextView) v.findViewById(R.id.text2);
		authorTextView.setText(review.getAuthor().getUsername());
		TextView ratingTextView = (TextView) v
				.findViewById(R.id.favorite_meal_rating);
		ratingTextView.setText(review.getRating());
		return v;
	}

}
