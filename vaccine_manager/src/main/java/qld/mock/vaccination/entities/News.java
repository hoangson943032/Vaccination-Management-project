package qld.mock.vaccination.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name = "NEWS", schema = "dbo")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "PREVIEW", nullable = false)
	private String preview;
	
	@Column(name = "CONTENT", nullable = false, length = 2000)
	private String content;
	
	@Column(name = "POST_DATE", nullable = false)
	private LocalDate postDate = LocalDate.now();
	


	public News() {
		super();
		// TODO Auto-generated constructor stub
	}

	public News(Integer id, String title, String preview, String content, LocalDate postDate) {
		super();
		this.id = id;
		this.title = title;
		this.preview = preview;
		this.content = content;
		this.postDate = postDate;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	
	
}
