package main;

import java.io.IOException;

import org.farng.mp3.TagException;

import utiles.elem.AudioFile;

public class MainAudioFile {

	private static String rute = "C:\\Users\\josec\\Documents\\";
	private static String name = "Halestorm - I Miss The Misery.mp3";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AudioFile af = new AudioFile(rute + name);

		af.setPathTag();

		try {
			af.sync();
		} catch (IOException | TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		af.readTag();
	}
}
