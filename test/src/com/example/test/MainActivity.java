package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

	private ArrayList<ListEntry> entriesArrayList = new ArrayList<ListEntry>();
	private ListAdapter entriesAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO: AsyncTask
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		entriesAdapter = new ListAdapter(this, R.layout.list_entry,
				entriesArrayList);
		setListAdapter(entriesAdapter);

		String get = get();
		try {
			JSONObject getKey = new JSONObject(get);
			String key = getKey.getString("key");

			String post = post(key);
			JSONObject postKey = new JSONObject(post);
			JSONArray getEntriesArray = postKey.getJSONArray("members");

			for (int i = 0; i < getEntriesArray.length(); i++) {
				JSONObject entry = getEntriesArray.getJSONObject(i);

				// Date Formatting
				// TODO: Incorporate Other Regions
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd", Locale.US);
				Date dateParse = new Date(Integer.parseInt(entry
						.getString("joined")));
				String date = dateFormat.format(dateParse);

				// Device & Model Concatenation
				entriesArrayList.add(new ListEntry(entry.getString("name"),
						date, entry.getString("device") + " "
								+ entry.getString("model"), entry
								.getString("bio")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String get() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();

		try {
			// Key/pair 'u/kwalker' added to URL with '?' for query
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

			// Key encoded with URL for post
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