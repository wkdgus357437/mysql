package movie.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import movie.bean.MovieDTO;
import movie.bean.TrailerDTO;

@Repository
public interface MovieDAO extends JpaRepository<MovieDTO, String>{
	
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_class = '1' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_boxoffice();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_already_released = '0' AND movieDTO.movie_class = '1' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_already_on_boxoffice();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_title like '%' || :keyword || '%' AND movieDTO.movie_class = '1' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovie_title_search_boxoffice(@Param("keyword")String keyword);
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_already_released = '1' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_tobereleased();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_title like '%' || :keyword || '%' AND movieDTO.movie_already_released = '1' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovie_title_search_tobereleased(@Param("keyword")String keyword);
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_class = '3' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_special();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_already_released = '0' AND movieDTO.movie_class = '3' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_already_on_special();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_title like '%' || :keyword || '%' AND movieDTO.movie_class = '3' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovie_title_search_special(@Param("keyword")String keyword);
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_class = '4' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_filmsociety();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_already_released = '0' AND movieDTO.movie_class = '4' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_already_on_filmsociety();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_title like '%' || :keyword || '%' AND movieDTO.movie_class = '4' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovie_title_search_filmsociety(@Param("keyword")String keyword);
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_class = '5' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_classicsociety();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_already_released = '0' AND movieDTO.movie_class = '5' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovieList_already_on_classicsociety();
	
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_title like '%' || :keyword || '%' AND movieDTO.movie_class = '5' ORDER BY movieDTO.movie_reserve_rate DESC")
	public List<MovieDTO> getMovie_title_search_classicsociety(@Param("keyword")String keyword);
	
	@Modifying
	@Transactional
	@Query(value="update movietable set movie_like = movie_like + 1 where movie_title = :movie_title", nativeQuery = true)
	public void movie_like_one(@Param("movie_title") String movie_title);
	
	@Modifying
	@Transactional
	@Query(value="update movietable set movie_like = movie_like - 1 where movie_title = :movie_title", nativeQuery = true)
	public void movie_like_minus_one(@Param("movie_title") String movie_title);
 
	
	//관리자 페이지 무비 리스트 삭제
	@Transactional 
	@Modifying
	@Query(value = "delete from movietable where movie_title =:movie_title", nativeQuery=true)
	public void deleteByAdmin_movie_delete(@Param("movie_title") String movie_title);

	@Query("select m.movie_poster_url from MovieDTO m where m.movie_title = ?1")
	public String getMovieURL(String title);

	
	//관리자 페이지 무비 리스트 검색
	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_title like '%' || :adminMovieSearchKeyword || '%'")
	public List<MovieDTO> getAdminMovieSearchTitle(@Param("adminMovieSearchKeyword")String adminMovieSearchKeyword);

	@Query("select movieDTO from MovieDTO movieDTO where movieDTO.movie_subtitle like '%' || :adminMovieSearchKeyword || '%'")
	public List<MovieDTO> getAdminMovieSearchSubTitle(@Param("adminMovieSearchKeyword")String adminMovieSearchKeyword);


}
