package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

public class MainActivity extends ListActivity {
	// References:
	// http://www.edumobile.org/android/android-development/json-example-in-android/
	// http://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/

	private ArrayList<ListItem> entries = new ArrayList<ListItem>();
	private ItemAdapter itemAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO: AsyncTask
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		itemAdapter = new ItemAdapter(this, R.layout.list_item, entries);
		setListAdapter(itemAdapter);

		String get = get();
		try {
			JSONObject getJsonObject = new JSONObject(get);
			// Log.i(MainActivity.class.getName(), get);
			String key = getJsonObject.getString("key");
			Log.i(MainActivity.class.getName(), key);

			String post = post(key);
			JSONObject postJSONObject = new JSONObject(post);
			JSONArray postJSONArray = postJSONObject.getJSONArray("members");

			Log.i(MainActivity.class.getName(),
					"Entries: " + postJSONArray.length());
			for (int i = 0; i < postJSONArray.length(); i++) {
				JSONObject entryJSONObject = postJSONArray.getJSONObject(i);
				entries.add(new ListItem(entryJSONObject.getString("name"),
						entryJSONObject.getString("joined"), entryJSONObject
								.getString("device"), entryJSONObject
								.getString("model"), entryJSONObject
								.getString("bio")));
				/*
				 * Log.i(MainActivity.class.getName(),
				 * entryJSONObject.getString("name"));
				 * Log.i(MainActivity.class.getName(),
				 * entryJSONObject.getString("joined"));
				 * Log.i(MainActivity.class.getName(),
				 * entryJSONObject.getString("device"));
				 * Log.i(MainActivity.class.getName(),
				 * entryJSONObject.getString("model"));
				 * Log.i(MainActivity.class.getName(),
				 * entryJSONObject.getString("bio"));
				 */
			}
			Log.i(MainActivity.class.getName(), post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String get() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		try {
			HttpGet httpGet = new HttpGet(
					"http://test.devicevault.net/request.init/?u=kwalker");
			HttpResponse response = client.execute(httpGet);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e(MainActivity.class.toString(), "Get Failed!");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public String post(String key) {
		StringBuilder builder = new StringBuilder();
		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpPost httppost = new HttpPost(
					"http://test.devicevault.net/request.generate");

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("key", key));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				// Verify Response: Log.i(JSONParser.class.getName(),
				// EntityUtils.toString(entity));
			} else {
				Log.e(MainActivity.class.toString(), "Post Failed!");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
}