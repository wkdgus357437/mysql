package user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import user.bean.BookDTO;

import java.util.List;

import javax.transaction.Transactional;

public interface BookDAO extends JpaRepository<BookDTO, Integer> {
    @Query("select bookDTO from BookDTO bookDTO where bookDTO.movie_date = :keyword order by bookDTO.pk asc ")
    public List<BookDTO> movieList(@Param("keyword")String movie_date);
    @Query("select bookDTO from BookDTO bookDTO where bookDTO.movie_date = :keyword and bookDTO.movie_title = :keyword2 order by bookDTO.movie_city desc ")
    public List<BookDTO> cityList(@Param("keyword2")String movie_title, @Param("keyword")String movie_date);
    @Query("select bookDTO from BookDTO bookDTO where bookDTO.movie_date = :keyword and bookDTO.movie_title = :keyword2 and bookDTO.movie_city = :keyword3 order by bookDTO.movie_cinema asc ")
    public List<BookDTO> cinemaList(@Param("keyword2")String movie_title, @Param("keyword")String movie_date, @Param("keyword3")String movie_city);
    @Query("select bookDTO from BookDTO bookDTO where bookDTO.movie_date = :keyword and bookDTO.movie_title = :keyword2 and bookDTO.movie_city = :keyword3 and bookDTO.movie_cinema = :keyword4 order by bookDTO.movie_time asc ")
    public List<BookDTO> timeList(@Param("keyword2")String movie_title, @Param("keyword")String movie_date, @Param("keyword3")String movie_city, @Param("keyword4")String movie_cinema);
    
    @Modifying(clearAutomatically = true)
	@Transactional
	@Query("update BookDTO bookDTO set movie_seat= ?2 where bookDTO.pk = ?1")
	void UpdateSeat(int pk,String movie_seat);
    
}
