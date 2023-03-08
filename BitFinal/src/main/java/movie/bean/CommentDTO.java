package movie.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="commenttable") 

public class CommentDTO {
	
	@Id
	@Column(name="user_name", nullable = false)
	private String user_name;
	
	@Column(name="movie_title")
	private String movie_title;
	
	@Column(name="user_rate")
	private String user_rate;
	
	@Column(name="user_content1")
	private String user_content1;
	
	@Column(name="user_content2")
	private String user_content2;
	
	@Column(name="user_content3")
	private String user_content3;
	
	@Column(name="user_content4")
	private String user_content4;
	
	@Column(name="user_content5")
	private String user_content5;
	
	@Column(name="user_story_recommant")
	private String user_story_recommant;
	

}
