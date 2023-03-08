package movie.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import movie.bean.CommentDTO;
import movie.bean.MovieDTO;
import movie.bean.TrailerDTO;
import movie.dao.CommentDAO;
import movie.dao.MovieDAO;
import movie.dao.TrailerDAO;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieDAO movieDAO; 
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private TrailerDAO trailerDAO;
	
	@Override
	public void write(MovieDTO movieDTO) {
		//DB
		//id컬럼이 primary key로 설정되어있기 때문에
		//똑같은 아이디가 없으면 insert로 수행이 되고, id가 있으면 update로 수행된다.
		movieDAO.save(movieDTO);
		System.out.println("write 성공");
	}

	@Override
	public List<MovieDTO> getMovieList_boxoffice() {
		//DB
		return movieDAO.getMovieList_boxoffice();
	}

	@Override
	public List<MovieDTO> getMovieList_special() {
		//DB
		return movieDAO.getMovieList_special();
	}
	
	@Override
	public List<MovieDTO> getMovieList_tobereleased() {
		//DB
		return movieDAO.getMovieList_tobereleased();
	}
	
	@Override
	public List<MovieDTO> getMovieList_filmsociety() {
		//DB
		return movieDAO.getMovieList_filmsociety();
	}
	
	@Override
	public List<MovieDTO> getMovieList_classicsociety() {
		//DB
		return movieDAO.getMovieList_classicsociety();
	}
	
	@Override
	public List<MovieDTO> getMovieList_already_on_boxoffice() {
		//DB
		return movieDAO.getMovieList_already_on_boxoffice();
	}
	
	
	@Override
	public List<MovieDTO> getMovieList_already_on_special() {
		//DB
		return movieDAO.getMovieList_already_on_special();
	}
	
	@Override
	public List<MovieDTO> getMovieList_already_on_filmsociety() {
		//DB
		return movieDAO.getMovieList_already_on_filmsociety();
	}
	
	@Override
	public List<MovieDTO> getMovieList_already_on_classicsociety() {
		//DB
		return movieDAO.getMovieList_already_on_classicsociety();
	}

	@Override
	public String isExistMovie_title(String movie_title) {
		System.out.println("아이디 조회 성공");
		//DB
		Optional<MovieDTO> MovieDTO = movieDAO.findById(movie_title);
		System.out.println(MovieDTO); //값이 없으면 Optional.empty 가 출력된다.
		
		if(MovieDTO.isPresent()) //값이 없으면 false
			return "exist";
		else {
			return "non_exist";
		}
	}


	@Override
	public void Movie_delete(String movie_title) {
		//DB
		//deleteById()는 내부적으로 findById를 우선수행하고 delete를 수행한다.
		//만약 아이디가 없으면 EmptyResultDataAccessException이 발생한다.
		
		//delete() 는 내부적으로 findById를 수행하지않고 바로 delete를 수행한다.
		movieDAO.deleteById(movie_title);
		
	}

	@Override
	public Optional<MovieDTO> getMovie(String movie_title) {
		return movieDAO.findById(movie_title);
	}


	@Override
	public List<MovieDTO> search_boxoffice(Map<String, String> map) {
		String searchOption = map.get("searchOption");
		String keyword = map.get("keyword");

		if(searchOption.equals("movie_title"))
			return movieDAO.getMovie_title_search_boxoffice(keyword);
		else
			return null;
	}
	
	public List<MovieDTO> search_tobereleased(Map<String, String> map) {
		String searchOption = map.get("searchOption");
		String keyword = map.get("keyword");

		if(searchOption.equals("movie_title"))
			return movieDAO.getMovie_title_search_tobereleased(keyword);
		else
			return null;
	}
	
	@Override
	public List<MovieDTO> search_filmsociety(Map<String, String> map) {
		String searchOption = map.get("searchOption");
		String keyword = map.get("keyword");

		if(searchOption.equals("movie_title"))
			return movieDAO.getMovie_title_search_filmsociety(keyword);
		else
			return null;
	}
	
	@Override
	public List<MovieDTO> search_classicsociety(Map<String, String> map) {
		String searchOption = map.get("searchOption");
		String keyword = map.get("keyword");

		if(searchOption.equals("movie_title"))
			return movieDAO.getMovie_title_search_classicsociety(keyword);
		else
			return null;
	}
	
	@Override
	public List<MovieDTO> search_special(Map<String, String> map) {
		String searchOption = map.get("searchOption");
		String keyword = map.get("keyword");

		if(searchOption.equals("movie_title"))
			return movieDAO.getMovie_title_search_special(keyword);
		else
			return null;
	}

	@Override
	public void movie_like_one(String movie_title) {
		movieDAO.movie_like_one(movie_title);
		System.out.println(movie_title);
	}
	
	@Override
	public void movie_like_minus_one(String movie_title) {
		movieDAO.movie_like_minus_one(movie_title);
		System.out.println(movie_title);
	}
	
	//관리자 페이지 무비 리스트
	@Override
	public List<MovieDTO> admin_movie_list() {
		return movieDAO.findAll();
	}
	
	//관리자 페이지 무비 삭제
	@Override
	public void admin_movie_delete(String movie_title) {
		movieDAO.deleteByAdmin_movie_delete(movie_title);
		
	}

	@Override
	public String getMovieURL(String title) {
		return movieDAO.getMovieURL(title);
	}	
	@Override
	public void MovieCommentWrite(CommentDTO commentDTO) {
		System.out.println("댓글작성 서비스구역");
		commentDAO.save(commentDTO);
	}
	
	@Override
	public List<TrailerDTO> getTrailerList(String title) {
		System.out.println("예고편 가져오기"); 
		return trailerDAO.getTrailerList(title);
	}

	@Override
	public List<CommentDTO> getComments(String title) {
		return commentDAO.getCommentList(title);
	}

	//	관리자 페이지 이미 등록된 무비리스트 검색 "타이틀"
	@Override
	public List<MovieDTO> adminMovieSearch(Map<String, String> map) {
		String adminMovieSearchOption = map.get("adminMovieSearchOption");
		String adminMovieSearchKeyword = map.get("adminMovieSearchKeyword");
		
		if(adminMovieSearchOption.equals("movie_title"))
			return movieDAO.getAdminMovieSearchTitle(adminMovieSearchKeyword);
		else
		return movieDAO.getAdminMovieSearchSubTitle(adminMovieSearchKeyword);
	}
}
