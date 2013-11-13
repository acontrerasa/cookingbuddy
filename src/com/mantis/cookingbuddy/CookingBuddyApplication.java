package com.mantis.cookingbuddy;

import java.util.List;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

public class CookingBuddyApplication extends Application {
	private List<GraphUser> selectedUsers;
    private GraphPlace selectedPlace;
    private static ImageLoader imgDownloader;
	@Override
	public void onCreate() {
		super.onCreate();
		ParseObject.registerSubclass(Recipe.class);
		Parse.initialize(this, Constants.PARSE_APPID, Constants.PARSE_CLIENT_KEY);

		ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access.
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
		ParseFacebookUtils.initialize(Constants.FACEBOOK_KEY);
		initImageLoader(getApplicationContext());
	}
	public static void initImageLoader(Context context) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
					.showImageForEmptyUri(R.drawable.ic_empty)
					.showImageOnFail(R.drawable.ic_error)
					.cacheInMemory(true)
					.cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
									.threadPriority(Thread.NORM_PRIORITY - 2)
									.defaultDisplayImageOptions(options)
									.denyCacheImageMultipleSizesInMemory()
									.discCacheFileNameGenerator(new Md5FileNameGenerator())
									.tasksProcessingOrder(QueueProcessingType.LIFO)
									.writeDebugLogs() // Remove for release app
									.build();
		CookingBuddyApplication.imgDownloader = ImageLoader.getInstance();
		CookingBuddyApplication.imgDownloader.init(config);
		CookingBuddyApplication.getImgDownloader().clearDiscCache();
		CookingBuddyApplication.getImgDownloader().clearMemoryCache();
	}
	public static ImageLoader getImgDownloader() {
        return CookingBuddyApplication.imgDownloader;
    }
	public List<GraphUser> getSelectedUsers() {
	        return selectedUsers;
	}

	public void setSelectedUsers(List<GraphUser> users) {
	        selectedUsers = users;
	}

	public GraphPlace getSelectedPlace() {
	        return selectedPlace;
	}
    public void setSelectedPlace(GraphPlace place) {
	        this.selectedPlace = place;
	}
}
