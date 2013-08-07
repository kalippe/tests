package com.example.test;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<ListEntry> {

	private ArrayList<ListEntry> entriesArrayList;

	public ListAdapter(Context context, int resource,
			ArrayList<ListEntry> entriesArrayList) {
		super(context, resource, entriesArrayList);
		this.entriesArrayList = entriesArrayList;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		// Rendering list_entry.xml for ListView in activity_main.xml
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_entry, null);
		}

		// Need position in ListView to populate
		ListEntry listEntries = entriesArrayList.get(position);

		// Populating list_entry.xml
		TextView name = (TextView) convertView.findViewById(R.id.name);
		name.setText("Name: ");
		TextView nameText = (TextView) convertView.findViewById(R.id.nametext);
		nameText.setText(listEntries.getName());

		TextView date = (TextView) convertView.findViewById(R.id.date);
		date.setText("Date: ");
		TextView dateText = (TextView) convertView.findViewById(R.id.datetext);
		dateText.setText(listEntries.getDate());

		TextView phone = (TextView) convertView.findViewById(R.id.phone);
		phone.setText("Phone: ");
		TextView phoneText = (TextView) convertView
				.findViewById(R.id.phonetext);
		phoneText.setText(listEntries.getPhone());

		TextView bio = (TextView) convertView.findViewById(R.id.bio);
		bio.setText("Biography: ");
		TextView bioText = (TextView) convertView.findViewById(R.id.biotext);
		bioText.setText(listEntries.getBio());

		return convertView;
	}
}