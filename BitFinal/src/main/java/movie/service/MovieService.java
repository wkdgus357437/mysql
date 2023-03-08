package movie.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import movie.bean.CommentDTO;
import movie.bean.MovieDTO;
import movie.bean.TrailerDTO;

public interface MovieService {
	
	public void write(MovieDTO movieDTO);
	
	public List<MovieDTO> getMovieList_boxoffice();
	
	public List<MovieDTO> getMovieList_special();
	
	public List<MovieDTO> getMovieList_tobereleased();

	public List<MovieDTO> getMovieList_filmsociety();

	public List<MovieDTO> getMovieList_classicsociety();
	
	public List<MovieDTO> getMovieList_already_on_boxoffice();


	public List<MovieDTO> getMovieList_already_on_special();

	public List<MovieDTO> getMovieList_already_on_classicsociety();
	
	public List<MovieDTO> getMovieList_already_on_filmsociety();
	
	public void Movie_delete(String movie_title);

	public Optional<MovieDTO> getMovie(String movie_title);

	public String isExistMovie_title(String movie_title);
	
	public List<MovieDTO> search_boxoffice(Map<String, String> map);

	public List<MovieDTO> search_tobereleased(Map<String, String> map);
	
	public List<MovieDTO> search_special(Map<String, String> map);

	public List<MovieDTO> search_classicsociety(Map<String, String> map);

	public List<MovieDTO> search_filmsociety(Map<String, String> map);

	public void movie_like_one(String movie_title);

	public void movie_like_minus_one(String movie_title);

	//관리자 페이지 무비 리스트 전체 가져오기 
	public List<MovieDTO> admin_movie_list();
	
	//관리자 페이지 무비 리스트 삭제
	public void admin_movie_delete(String movie_title); 
		
	public void MovieCommentWrite(CommentDTO commentDTO);

	public List<TrailerDTO> getTrailerList(String title);

	public List<CommentDTO> getComments(String title);

	public String getMovieURL(String title);
		
	//관리자 페이지 무비리스트 검색
	public List<MovieDTO> adminMovieSearch(Map<String, String> map);



}
