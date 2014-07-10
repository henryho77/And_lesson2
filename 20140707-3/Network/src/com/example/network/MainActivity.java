package com.example.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends ActionBarActivity {

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
 		StrictMode.setThreadPolicy(policy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		private TextView textView;

		public PlaceholderFragment() {
		}

		public String fetch(String urlStr) {
	 
 			try {
 				URL url = new URL(urlStr);
 				URLConnection urlConnection = url.openConnection();
 
 				InputStream is = urlConnection.getInputStream();
 				BufferedReader buffer = new BufferedReader(
 						new InputStreamReader(is));
 
 				String result = "";
 				String line;
 				while ((line = buffer.readLine()) != null) {
 					result += line;
 				}
 
 				JSONObject object = new JSONObject(result);
 				return object.getJSONArray("results").getJSONObject(0)
 						.getString("formatted_address");
 
 			} catch (MalformedURLException e) {
 				e.printStackTrace();
 			} catch (IOException e) {
 				e.printStackTrace();
 			} catch (JSONException e) {
 				e.printStackTrace();
 			}
 			return null;
	 	}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			textView = (TextView) rootView.findViewById(R.id.textView);
 			String url = "http://maps.googleapis.com/maps/api/geocode/json?address=taipei101&sensor=false";
 			String result = fetch(url);
 
 			textView.setText(result);
			
			return rootView;
		}
	}
}
