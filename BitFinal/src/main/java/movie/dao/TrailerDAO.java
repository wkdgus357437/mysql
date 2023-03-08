package movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import movie.bean.TrailerDTO;

@Repository
public interface TrailerDAO extends JpaRepository<TrailerDTO, String>{
	
	@Query("select t from TrailerDTO t where t.movie_title = ?1")
	public List<TrailerDTO> getTrailerList(String title);

}
