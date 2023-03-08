package user.bean;

import java.sql.Timestamp;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(
        name = "Reservation_SEQ_GENERATOR"
        , sequenceName = "Reservation_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
@Table(name="reservation")
public class ReservationDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Reservation_SEQ_GENERATOR")
	@Column(name="pk")
	private int pk;
	
	@Column(name="user_id",length = 300)
	private String user_id;
	
	@Column(name="book_pk",length = 100)
	private int book_pk;
	
	@Column(name = "movie_cinema",length = 300)
    private String movie_cinema;
	
	@Column(name = "movie_theater", length = 300)
    private String movie_theater;
	
	@Column(name = "movie_title", length = 300)
    private String movie_title;
	
	
	@Column(name = "movie_date", length = 300)
    private String movie_date;
	
	@Column(name = "movie_time", length = 300)
    private String movie_time;
	
	@Column(name = "movie_seat",  length = 1000, nullable = false)
    private String movie_seat;
	
	@Column(name = "movie_status", length = 300)
	private String movie_status;
	
	@Column(name = "reserve_time", length = 300)
	@CreationTimestamp
	private Timestamp reserve_time;
	
	
	
}
