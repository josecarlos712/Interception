package main;

import utiles.AudioFile;

public class MainAudioFile {

	private static String rute = "C:\\Users\\josec\\Desktop\\Jose Carlos\\Musica\\";
	private static String name = "ACDC - Back in Black.mp3";

	public void main() {
		// TODO Auto-generated method stub

		AudioFile af = new AudioFile(rute + name);
		af.readTags();
		af.setArtist("a");
		af.setAlbum("af.getAlbum");
		af.setTitle("titulo");
		af.setPathTags();
		af.readTags();
	}
}
