package com.example.test;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<ListItem> {

	private ArrayList<ListItem> objects;

	public ItemAdapter(Context context, int resource,
			ArrayList<ListItem> objects) {
		super(context, resource, objects);
		this.objects = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
		}

		ListItem listItem = objects.get(position);

		if (listItem != null) {

			TextView nameTag = (TextView) v.findViewById(R.id.nametag);
			TextView nameText = (TextView) v.findViewById(R.id.nametext);
			TextView joinedTag = (TextView) v.findViewById(R.id.joinedtag);
			TextView joinedText = (TextView) v.findViewById(R.id.joinedtext);
			TextView deviceTag = (TextView) v.findViewById(R.id.devicetag);
			TextView deviceText = (TextView) v.findViewById(R.id.devicetext);
			TextView modelTag = (TextView) v.findViewById(R.id.modeltag);
			TextView modelText = (TextView) v.findViewById(R.id.modeltext);
			TextView bioTag = (TextView) v.findViewById(R.id.biotag);
			TextView bioText = (TextView) v.findViewById(R.id.biotext);

			if (nameTag != null) {
				nameTag.setText("Name: ");
			}
			if (nameText != null) {
				nameText.setText(listItem.getName());
			}
			if (joinedTag != null) {
				joinedTag.setText("Joined: ");
			}
			if (joinedText != null) {
				joinedText.setText(listItem.getJoined());
			}
			if (deviceTag != null) {
				deviceTag.setText("Device: ");
			}
			if (deviceText != null) {
				deviceText.setText(listItem.getDevice());
			}
			if (modelTag != null) {
				modelTag.setText("Model: ");
			}
			if (modelText != null) {
				modelText.setText(listItem.getModel());
			}
			if (bioTag != null) {
				bioTag.setText("Bio: ");
			}
			if (bioText != null) {
				bioText.setText(listItem.getBio());
			}
		}
		return v;
	}
}