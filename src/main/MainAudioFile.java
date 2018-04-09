package main;

import utiles.AudioFile;

public class MainAudioFile {

	private static String rute = "C:\\Users\\josec\\Documents\\";
	private static String name = "Adele - Someone Like You.mp3";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AudioFile af = new AudioFile(rute + name);
		af.readTags();
		af.setArtist("a");
		af.setAlbum("af.getAlbum");
		af.setTitle("titulo");
		// af.setPathTags();
		af.readTags();
	}
}
