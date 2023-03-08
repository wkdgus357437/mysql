package user.dao;

import java.util.List;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import user.bean.ReservationDTO;


@Repository
public interface ReservationDAO extends JpaRepository<ReservationDTO, Integer>{

	@Query("select max(reservationDTO.pk) from ReservationDTO reservationDTO")
	int getSeq();

	@Query("select reservationDTO from ReservationDTO reservationDTO where reservationDTO.user_id = ?1 order by reservationDTO.reserve_time desc")
	List<ReservationDTO> getReservation(String id);

	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("update ReservationDTO reservationDTO set movie_status= '상영' where reservationDTO.movie_date||' '||reservationDTO.movie_time <= to_char((sysdate + 9/24 ),('yyyy-mm-dd hh24:mi'))")
	void movieStatusChecker();

}
