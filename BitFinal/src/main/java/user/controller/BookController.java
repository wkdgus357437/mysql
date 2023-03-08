package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.bean.BookDTO;
import user.dao.BookDAO;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping(value="book")
public class BookController {

    @Autowired
    private BookDAO bookDAO;

    //@ResponseBody //이거그거 jsp로 안읽고 한글로 읽던게 바꾸는 그건데 아무튼 여기서는 그 에이작스로 돌아가게만드는용도임 안쓰면 디스페처로 가서 지랄지랄
    @PostMapping("write")
    public void write(@RequestBody BookDTO bookDTO) {
            bookDAO.save(bookDTO);
    }

    @PostMapping("movieList")
    public List<BookDTO> movieList(@RequestParam String movie_date) {
        System.out.println(movie_date);
        return bookDAO.movieList(movie_date);
    }

    @PostMapping("cityList")
    public List<BookDTO> cityList(@RequestParam String movie_title, String movie_date) {

        return bookDAO.cityList(movie_title,movie_date);
    }

    @PostMapping("cinemaList")
    public List<BookDTO> cinemaList(@RequestParam String movie_title, String movie_date, String movie_city) {

        return bookDAO.cinemaList(movie_title,movie_date,movie_city);
    }

    @PostMapping("timeList")
    public List<BookDTO> timeList(@RequestParam String movie_title, String movie_date, String movie_city, String movie_cinema) {

        return bookDAO.timeList(movie_title,movie_date,movie_city,movie_cinema);
    }

    
/*------------------------박지훈---------------------------*/
    
    @GetMapping(value = "getSeat")
	public BookDTO getSeat(@RequestParam int pk) {
		return bookDAO.findById(pk).orElse(null);
	}
    
    
    
    @PostMapping(value = "addSeat")
	public void addSeat(@RequestBody  Map<String, Object> map) {
		int pk = (int) map.get("pk");
		String movie_seat= (String) map.get("movie_seat");
		bookDAO.UpdateSeat(pk,movie_seat);
	}
    
    
}
