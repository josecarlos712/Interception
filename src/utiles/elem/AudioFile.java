package utiles.elem;

import java.io.File;
import java.io.IOException;

import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.ID3v2_3;

public class AudioFile {

	private String path, title, album, artist, pathTitle, pathArtist;
	private org.farng.mp3.MP3File song;
	private AbstractMP3Tag tag;
	private File file;

	public AudioFile(String path) {
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
		if (this.song.hasID3v2Tag())
			this.tag = song.getID3v2Tag();
		else
			this.tag = new ID3v2_3();
		// Path tags
		setPathTags();
		/*	System.out.println("pathRute: " + this.path);
			System.out.println("pathTitle: " + this.pathTitle);
			System.out.println("pathArtist: " + this.pathArtist);*/

		readTag();
	}

	public void readTag() {
		//System.out.println("------------readTags()-------------");

		this.title = this.tag.getSongTitle();
		this.album = this.tag.getAlbumTitle();
		this.artist = this.tag.getLeadArtist();

		/*	System.out.println("Title: " + this.title);
			System.out.println("Album: " + this.album);
			System.out.println("Artist: " + this.artist);
			// System.out.println("tag: " + this.tag.toString());
			System.out.println("------------------------------------");
			System.out.println("------------------------------------");*/
	}

	public void sync() throws IOException, TagException { // Sincroniza la etiqueta creada con el archivo y lo guarda
		this.tag.setSongTitle(this.title);
		this.tag.setAlbumTitle(this.album);
		this.tag.setLeadArtist(this.artist);

		this.song.setID3v2Tag(this.tag);
		this.song.save();
	}

	// Getters y Setters
	public void setPathTag() {
		this.title = this.pathTitle;
		this.artist = this.pathArtist;
		if (this.album == null || this.album == "")
			this.album = "Music";
	}

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

	public String getNameFile() {
		return this.file.getName();
	}

	private void setPathTags() {
		try {
			String aux = file.getName().split(" - ")[1];
			System.out.println("Path: " + aux);
			try {
				this.pathTitle = aux.substring(0, aux.indexOf("."));
			} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				this.pathTitle = "ERROR!";
			}
			try {
				this.pathArtist = file.getName().split("-")[0].trim();
			} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
				this.pathTitle = "ERROR!";
			}
		} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
			this.pathTitle = file.getName().substring(0, file.getName().indexOf("."));
			this.pathArtist = "Unknown";
		}
	}
}