package qld.mock.vaccination.dto;

import javax.validation.constraints.NotEmpty;

public class NewsDto {

	@NotEmpty(message ="Title cannot be empty.")
	private String title;
	
	@NotEmpty(message ="Preview cannot be empty.")
	private String preview;
	
	@NotEmpty(message ="Content cannot be empty.")
	private String content;
	public NewsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewsDto(String title, String preview, String content) {
		super();
		this.title = title;
		this.preview = preview;
		this.content = content;
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	
}
