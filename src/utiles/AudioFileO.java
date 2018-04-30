package utiles;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.ID3v2_3;

public class AudioFileO {

	private String path, title, album, artist, pathTitle, pathArtist;
	private org.farng.mp3.MP3File song;
	private AbstractID3v2 tag;
	private File file;

	public AudioFileO(String path) {
		this.path = path;
		this.file = new File(this.path);

		try {
			this.song = new MP3File(this.file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Tags
		if (song.hasID3v2Tag())
			this.tag = song.getID3v2Tag();
		else
			this.tag = new ID3v2_3();
		// Path tags
		String aux = file.getName().split(" - ")[1];
		System.out.println("Path: " + aux);
		this.pathTitle = aux.substring(0, aux.indexOf("."));
		this.pathArtist = file.getName().split("-")[0].trim();
		aux.substring(aux.indexOf(".") + 1);

		System.out.println("pathRute: " + this.path);
		System.out.println("pathTitle: " + this.pathTitle);
		System.out.println("pathArtist: " + this.pathArtist);
	}

	public void readTag() {

		if (this.song.hasID3v2Tag())
			this.tag = this.song.getID3v2Tag();
		else
			this.tag = new ID3v2_3();

		System.out.println("------------readTags()-------------");

		this.title = this.tag.getSongTitle();
		this.album = this.tag.getAlbumTitle();
		this.artist = this.tag.getLeadArtist();
		System.out.println("tag: " + tag.toString());

		System.out.println("------------------------------------");
	}

	@SuppressWarnings("rawtypes")
	public void sync() throws IOException, TagException {
		this.tag.setSongTitle(this.title);
		this.tag.setAlbumTitle(this.album);
		this.tag.setLeadArtist(this.artist);

		/*
		 * this.song.setID3v2Tag(this.tag); song.save();
		 */

		final Iterator ite = this.tag.iterator();
		while (ite.hasNext()) {
			final AbstractID3v2Frame frame = (AbstractID3v2Frame) ite.next();
			final String identifier = frame.getIdentifier();
			AbstractID3v2Frame fr = this.tag.getFrame(identifier);
			// ID3v2_4Frame fr4 = new ID3v2_4Frame(fr);
			song.setFrameAcrossTags(fr);
		}
	}

	// Getters y Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}