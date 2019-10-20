package fi.serverprogrammingcourse.runningdatabase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
public class FileDummy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "filename")
	@NotNull
	private String fileName;
	
	@Column(name = "mimetype")
	@NotNull
	private String mimeType;
	
	@Column(name = "base64str")
	private String base64str;

	@Lob
	private byte[] file;

	public FileDummy() {
	
	}

	public FileDummy(String fileName, String mimeType, byte[] file) {
		super();
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.file = file;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getBase64str() {
		return base64str;
	}

	public void setBase64str(String base64str) {
		this.base64str = base64str;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
