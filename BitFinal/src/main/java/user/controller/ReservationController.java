package user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import user.bean.ReservationDTO;
import user.dao.ReservationDAO;

@CrossOrigin
@RestController
@RequestMapping(value = "reservation")
public class ReservationController {
	
	@Autowired
    private ReservationDAO reservationDAO;
	
	@PutMapping(value = "reservation")
	public void reservation(@RequestBody  Map<String, Object> map) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setUser_id(map.get("user_id").toString());
		reservationDTO.setBook_pk((int)map.get("book_pk"));
		reservationDTO.setMovie_cinema(map.get("movie_cinema").toString());
		reservationDTO.setMovie_theater(map.get("movie_theater").toString());
		reservationDTO.setMovie_date(map.get("movie_date").toString());
		reservationDTO.setMovie_time(map.get("movie_time").toString());
		reservationDTO.setMovie_title(map.get("movie_title").toString());
		reservationDTO.setMovie_seat(map.get("selectedSeat").toString());
		reservationDTO.setMovie_status("미상영");
		reservationDAO.save(reservationDTO);	
	}
	
	@GetMapping(value = "getSeq")
	public int getSeq() {
		return reservationDAO.getSeq();			
	}
	
	@GetMapping(value = "getReservation")
	public List<ReservationDTO> getReservation(@RequestParam String id){
		return reservationDAO.getReservation(id);
	}
	
	@DeleteMapping(value = "cancelReservation")
	public void cancelReservation(@RequestParam int pk) {
		reservationDAO.deleteById(pk);
	}
	
	@Scheduled(fixedRate = 60000)
	public void movieStatusChecker() {
		reservationDAO.movieStatusChecker();
	}
	
}
