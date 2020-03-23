
//Kanhang Li and Nobel Ibe

package model;

import java.util.Comparator;

public class Song {

	String name;
	String artist;
	String album;
	String year;

	public Song(String name, String artist, String album, String year) {
		super();
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;

	}
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Name:"+name+" "+"Artist:"+artist+" "+"album:"+album+" "+"year:"+year;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public static Comparator<Song>nameSort = new Comparator<Song>() {

		public int compare(Song song1, Song song2) {
		   String SongTitle1 = song1.getName().toUpperCase();
		   String SongTitle2 = song2.getName().toUpperCase();
		   return SongTitle1.compareTo(SongTitle2);
	}};
	public static Comparator<Song> artistSort = new Comparator<Song>() {

		public int compare(Song song1, Song song2) {
		   String SongArtist1 = song1.getArtist().toUpperCase();
		   String SongArtist2 = song2.getArtist().toUpperCase();
		   return SongArtist1.compareTo(SongArtist2);
	}};
	
	

}
