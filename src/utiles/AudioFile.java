package utiles;

import java.io.File;

import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.MediaFile;
import org.blinkenlights.jid3.v1.ID3V1_0Tag;
import org.blinkenlights.jid3.v2.ID3V2Tag;
import org.blinkenlights.jid3.v2.ID3V2_3_0Tag;

public class AudioFile {

	private String path, title, album, artist, format, pathTitle, pathArtist;
	private MediaFile mediaFile;
	private ID3V2Tag tag = null;
	private File file;

	public AudioFile(String path) {
		this.file = new File(path);
		this.path = path;
		this.title = "No title";
		this.album = "Music";
		this.artist = "Unknown";
		this.album = "";
		String aux = file.getName().split(" - ")[1];
		this.pathTitle = aux.substring(0, aux.indexOf("."));
		this.pathArtist = file.getName().split("-")[0].trim();
		this.format = aux.substring(aux.indexOf(".") + 1);
		System.out.println("pathTitle: " + this.pathTitle);
		System.out.println("pathArtist: " + this.pathArtist);
		System.out.println("format: " + this.format);
	}

	public void readTags() {
		// System.out.println("------------readTags()-------------");
		this.mediaFile = new MP3File(new File(this.path));
		try {
			for (Object obj : mediaFile.getTags()) {
				if (obj instanceof ID3V2_3_0Tag)
					readID3V2_3Tags(obj);
				else if (obj instanceof ID3V1_0Tag)
					readID3V1Tags(obj);
			}
		} catch (ID3Exception e1) {
			System.out.println("Error en la ruta del archivo " + this.path);
		}
		// System.out.println("------------------------------------");
	}

	private void readID3V1Tags(Object obj) { // Obtiene las etiquetas de ID3v1
		ID3V1_0Tag oID3V1_0Tag = (ID3V1_0Tag) obj;
		if (oID3V1_0Tag.getTitle() != null) {
			System.out.println("Titulo ID3v1: " + oID3V1_0Tag.getTitle());
			this.title = oID3V1_0Tag.getTitle();
		}
		if (oID3V1_0Tag.getAlbum() != null) {
			System.out.println("Album ID3v1: " + oID3V1_0Tag.getAlbum());
			this.album = oID3V1_0Tag.getAlbum();
		}
		if (oID3V1_0Tag.getArtist() != null) {
			System.out.println("Artista ID3v1: " + oID3V1_0Tag.getArtist());
			this.artist = oID3V1_0Tag.getArtist();
		}
	}

	private void readID3V2_3Tags(Object obj) { // Obtiene las etiquetas e ID3v2
		ID3V2_3_0Tag oID3V2_3_0Tag = (ID3V2_3_0Tag) obj;
		if (oID3V2_3_0Tag.getTitle() != null) {
			System.out.println("Titulo ID3v2: " + oID3V2_3_0Tag.getTitle());
			this.title = oID3V2_3_0Tag.getTitle();
		}
		if (oID3V2_3_0Tag.getAlbum() != null) {
			System.out.println("Album ID3v2: " + oID3V2_3_0Tag.getAlbum());
			this.album = oID3V2_3_0Tag.getAlbum();
		}
		if (oID3V2_3_0Tag.getArtist() != null) {
			System.out.println("Artista ID3v2: " + oID3V2_3_0Tag.getArtist());
			this.artist = oID3V2_3_0Tag.getArtist();
		}
	}

	// Getters
	public String getTitle() {
		return this.title;
	}

	public String getArtist() {
		return this.artist;
	}

	public String getAlbum() {
		return this.album;
	}

	public String getPath() {
		return this.path;
	}

	// Setters
	public void setTitle(String title) {
		try {
			tag = this.mediaFile.getID3V2Tag();
			tag.setTitle(title);
		} catch (ID3Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Error al cambiar el titulo");
		}
		mediaFile.setID3Tag(tag);
		sync();
	}

	public void setAlbum(String album) {
		try {
			tag = this.mediaFile.getID3V2Tag();
			tag.setAlbum(album);
		} catch (ID3Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Error al cambiar el album");
		}
		mediaFile.setID3Tag(tag);
		sync();
	}

	public void setArtist(String artist) {
		try {
			tag = this.mediaFile.getID3V2Tag();
			tag.setArtist(artist);
		} catch (ID3Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Error al cambiar el artista");
		}
		mediaFile.setID3Tag(tag);
		sync();
	}

	public void sync() {
		try {
			mediaFile.sync();
		} catch (ID3Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPathTags() {
		this.setTitle(pathTitle);
		this.setArtist(pathArtist);
		if (Cf.blankAlbum)
			this.setAlbum("");
	}
}