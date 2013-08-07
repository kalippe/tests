package com.example.test;

public class ListEntry {
	private String name;
	private String date;
	private String phone;
	private String bio;

	public ListEntry(String name, String date, String phone, String bio) {
		this.name = name;
		this.date = date;
		this.phone = phone;
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}