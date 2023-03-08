package movie.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adminBoard.bean.AdminBoardDTO;

import javax.servlet.http.HttpSession;

import movie.bean.CommentDTO;
import movie.bean.MovieDTO;
import movie.bean.TrailerDTO;
import movie.service.MovieService;

@CrossOrigin  //(origins = "http://localhost:3000") 하면 지정된 port만 들어올수있다.
@RestController		//@RestController = @Controller + @ResponseBody
@RequestMapping(path = "movielist")
public class MovieController {
	@Autowired
	private MovieService MovieService;
	
	@GetMapping(path = "getMovieList_boxoffice")
	public List<MovieDTO> getMovieList_boxoffice() {
		return MovieService.getMovieList_boxoffice();
	}
	@GetMapping(path = "getMovieList_tobereleased")
	public List<MovieDTO> getMovieList_tobereleased() {
		return MovieService.getMovieList_tobereleased();
	}
	
	@GetMapping(path = "getMovieList_special")
	public List<MovieDTO> getMovieList_special() {
		return MovieService.getMovieList_special();
	}
	
	@GetMapping(path = "getMovieList_filmsociety")
	public List<MovieDTO> getMovieList_filmsociety() {
		return MovieService.getMovieList_filmsociety();
	}
	
	@GetMapping(path = "getMovieList_classicsociety")
	public List<MovieDTO> getMovieList_classicsociety() {
		return MovieService.getMovieList_classicsociety();
	}
	
	@GetMapping(path = "getMovieList_already_on_boxoffice")
	public List<MovieDTO> getMovieList_already_on_boxoffice() {
		return MovieService.getMovieList_already_on_boxoffice();
		
	}

	
	@GetMapping(path = "getMovieList_already_on_special")
	public List<MovieDTO> getMovieList_already_on_special() {
		return MovieService.getMovieList_already_on_special();
		
	}
	
	@GetMapping(path = "getMovieList_already_on_filmsociety")
	public List<MovieDTO> getMovieList_already_on_filmsociety() {
		return MovieService.getMovieList_already_on_filmsociety();
		
	}
	
	@GetMapping(path = "getMovieList_already_on_classicsociety")
	public List<MovieDTO> getMovieList_already_on_classicsociety() {
		return MovieService.getMovieList_already_on_classicsociety();
		
	}
	
	//세번째
	@PostMapping(path = "write")
	public void write(@ModelAttribute MovieDTO movieDTO) {
		
		MovieService.write(movieDTO);
		
	}

	
	@GetMapping(path="isExistMovie_title")
	public String isExistMovie_title(@RequestParam String movie_title) {
		System.out.println("isExistMovie 실행중");
		return MovieService.isExistMovie_title(movie_title);
	}
	
	@GetMapping(path="movie_like_one")
	public void movie_like_one(@RequestParam String movie_title) {
		MovieService.movie_like_one(movie_title);
	}
	
	@GetMapping(path="movie_like_minus_one")
	public void movie_like_minus_one(@RequestParam String movie_title) {
		MovieService.movie_like_minus_one(movie_title);
	}
	@DeleteMapping(path = "Movie_delete")
	public void Movie_delete(@RequestParam String id) {
		MovieService.Movie_delete(id);
	}

	// name="img" 1개 이상일 경우
	@PostMapping(value = "/upload")
	@ResponseBody
	public void upload(@RequestPart MultipartFile img, HttpSession session) {
		
		//실제폴더
		String filePath = session.getServletContext().getRealPath("/public/storage");
		System.out.println("실제폴더 : "+filePath);
		String fileName = img.getOriginalFilename();
		
		File file = new File(filePath, fileName);
		
		try {
			//FileCopyUtils.copy(upload.getInputStream(), new FileOutputStream(file)); // 가상폴더로 복사한다
			img.transferTo(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//복사
	}
	
	@GetMapping(path="getMovie")
	public Optional<MovieDTO> getMovie(@RequestParam String id) {
		System.out.println(MovieService.getMovie(id));
		System.out.println("movie_title = "+ id);
		return MovieService.getMovie(id);
	}
	
	@GetMapping(value="Movie_search_boxoffice")
	public List<MovieDTO>search_boxoffice(@RequestParam Map<String, String> map) { //searchOption, keyword
		System.out.println(map);
		return MovieService.search_boxoffice(map);
	}
	
	@GetMapping(value="Movie_search_tobereleased")
	public List<MovieDTO>search_tobereleased(@RequestParam Map<String, String> map) { //searchOption, keyword
		System.out.println(map);
		return MovieService.search_tobereleased(map);
	}
	
	@GetMapping(value="Movie_search_special")
	public List<MovieDTO>search_special(@RequestParam Map<String, String> map) { //searchOption, keyword
		System.out.println(map);
		return MovieService.search_special(map);
	}
	
	@GetMapping(value="Movie_search_filmsociety")
	public List<MovieDTO>search_filmsociety(@RequestParam Map<String, String> map) { //searchOption, keyword
		System.out.println(map);
		return MovieService.search_filmsociety(map);
	}
	
	@GetMapping(value="Movie_search_classicsociety") 
	public List<MovieDTO>search_classicsociety(@RequestParam Map<String, String> map) { //searchOption, keyword
		System.out.println(map);
		return MovieService.search_classicsociety(map);
	}
	
	//관리자 페이지 무비 리스트 가져오기
	@GetMapping(value = "admin_movie_list")
	public List<MovieDTO> admin_movie_list(){
		return MovieService.admin_movie_list();
	}
	
	//관리자 페이지 무비리스트 삭제
	@DeleteMapping(path = "admin_movie_delete")
	public void admin_movie_delete(@RequestParam String movie_title) {
		MovieService.admin_movie_delete(movie_title);
	}
	
	//관리자 페이지 현재 등록된 무비리스트 검색하기 
	@GetMapping(path = "adminMovieSearch")
	public List<MovieDTO> adminMovieSearch(@RequestParam Map<String, String> map){
		return MovieService.adminMovieSearch(map);
	}
	
	//---- 박지훈
	@GetMapping(value = "getMovieURL")
    public String getMovieURL(@RequestParam String title) {
		return MovieService.getMovieURL(title);
	}
    //-----
	
	@PostMapping(path="user_comment_write")
	public void userCommentWrite(@ModelAttribute CommentDTO commentDTO) {
		System.out.println("댓글작성 컨트롤러 구역");
		System.out.println("commentDTO+++++++"+commentDTO);
		MovieService.MovieCommentWrite(commentDTO);
	}
	@GetMapping(path="get_trailer_list")
	public List<TrailerDTO> getTrailerList(@RequestParam String title){
		System.out.println("트레일러 리스트 내놔" + MovieService.getTrailerList(title));
		return MovieService.getTrailerList(title);
	}

	@GetMapping(path = "getComments")
	public List<CommentDTO> getComments(@RequestParam String title){
		System.out.println("list =================================="+MovieService.getComments(title));
		return MovieService.getComments(title);
	}
	
}