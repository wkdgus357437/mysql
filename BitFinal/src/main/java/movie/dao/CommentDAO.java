package movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movie.bean.CommentDTO;

@Repository
public interface CommentDAO extends JpaRepository<CommentDTO, String>{
	
	@Query("select c from CommentDTO c where c.movie_title = ?1") 
	public List<CommentDTO> getCommentList(String title);

}
