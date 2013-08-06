package com.example.test;

public class ListItem {
	private String name;
	private String joined;
	private String device;
	private String model;
	private String bio;

	public ListItem() {

	}

	public ListItem(String name, String joined, String device, String model,
			String bio) {
		this.name = name;
		this.joined = joined;
		this.device = device;
		this.model = model;
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJoined() {
		return joined;
	}

	public void setJoined(String joined) {
		this.joined = joined;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}