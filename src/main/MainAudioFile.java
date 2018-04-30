package main;

import java.io.IOException;

import org.farng.mp3.TagException;

import utiles.AudioFileO;

public class MainAudioFile {

	private static String rute = "C:\\Users\\josec\\Documents\\";
	private static String name = "Alan Walker - Faded.mp3";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AudioFileO af = new AudioFileO(rute + name);
		af.readTag();

		af.setAlbum("New um");
		af.setArtist("New Artist");
		af.setTitle("New Title");

		try {
			af.sync();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		af.readTag();
	}
}
