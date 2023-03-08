package user.bean;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(
        name = "movieBook_SEQ_GENERATOR"
        , sequenceName = "movieBook_SEQ"
        , allocationSize = 1
)
@Table(name = "movie_book")
public class BookDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieBook_GENERATOR")//특정 데이터베이스에 맞게 자동으로 생성하는방식, 자동으로 시퀀스케이블이 생성된다.
    @Column(name="pk")
    private int pk;
    @Column(name = "movie_title",  length = 300)
    private String movie_title;
    @Column(name = "movie_date", length = 300)
    private String movie_date;

    @Column(name = "movie_theater", length = 300)
    private String movie_theater;

    @Column(name = "movie_time", length = 300)
    private String movie_time;

    @Column(name = "movie_city", length = 300)
    private String movie_city;
    @Column(name = "movie_cinema",length = 300)
    private String movie_cinema;

    @Column(name = "movie_age",  length = 300)
    private String movie_age;
    @Column(name = "movie_seat",  length = 1000)
    private String movie_seat;
}
