package movie.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@Table(name = "movietable")
@SequenceGenerator(name = "MOVIETABLE_SEQ_GENERATOR"
		, sequenceName = "MOVIETABLE_SEQ"
		, initialValue = 1
		, allocationSize = 1
)
public class MovieDTO {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIETABLE_SEQ_GENERATOR")
	@Column(name = "movie_number")
	private int movie_number;    //시퀀스 int로 해야 데이터 null 안됨 

	@Id
	@Column(name = "movie_title",nullable = false, length = 100)
	private String movie_title;  //영화이름 pk 영화이름은 같을수 가 없음

	@Column(name = "movie_subtitle", length = 100)
	private String movie_subtitle;  //서브

	@Column(name="movie_poster_url")
	private String movie_poster_url;  //

	@Column(name="movie_header_url")
	private String movie_header_url;

	@Column(name="movie_already_released", length = 5) 
	private int movie_already_released;

	@Column(name="movie_release_start", nullable = false, length = 30)
	private String movie_release_start;

	@Column(name="movie_release_end", nullable = false, length = 30)
	private String movie_release_end;

	@Column(name="movie_class", nullable = false, length = 100)
	private String movie_class;

	@Column(name="movie_agegrade", length = 100)
	private String movie_agegrade;
	
	@Column(name="movie_score", length = 100)
	private String movie_score;

	@Column(name = "movie_reserve_rate", length = 1000)
	@ColumnDefault("0")
	private String movie_reserve_rate;

	@Column(name="movie_like", length = 100)
	@ColumnDefault("0")
	private int movie_like;
	
	@Column(name = "movie_totalspactators", length = 100)
	private String movie_totalspactators;

	@Column(name = "movie_info_title",length = 1000)
	private String movie_info_title;

	@Column(name = "movie_info_title2" ,length = 1000)
	private String movie_info_title2;

	@Column(name = "movie_info_type" ,length = 1000)
	private String movie_info_type;

	@Column(name = "movie_info_point1",length = 1000)
	@ColumnDefault("0")
	private String movie_info_point1;
	
	@Column(name = "movie_info_point2",length = 1000)
	@ColumnDefault("0")
	private String movie_info_point2;
	
	@Column(name = "movie_info_point3",length = 1000)
	@ColumnDefault("0")
	private String movie_info_point3;
	
	@Column(name = "movie_info_point4",length = 1000)
	@ColumnDefault("0")
	private String movie_info_point4;
	
	@Column(name = "movie_info_point5",length = 1000)
	@ColumnDefault("0")
	private String movie_info_point5;

}

	/*String movie_number = page 이동할때 number부여로 페이지이동
		String movie_title = 영화제목
		String movie_subtitle = 영화 부제목
		String movie_poster_url = 전체영화, 영화소개에 들어가는 영화poster
		String movie_header_url = 영화소개에 들어가는 background poster
		int movie_already_released = 영화 개봉중/개봉예정을 구분
		String movie_release_start = 영화개봉날짜
		String movie_release_end = 영화개봉종료
		String movie_class = class로 나눠서 타입별 영화탭 구분.
		String movie_agegrade = 영화나이제한
		int movie_like = 좋아요 갯수 (쓰지않을 예정이지만 추가는 해놓음)
		String movie_reserve_rate = 예매율 순위대로 리스트나열예정
		String movie_score = 영화소개부분의 영화평점
		String movie_ranking = 영화소개부분의 영화순위
		String movie_totalspactators = 영화소개부분의 총관람인원
		String movie_info_title = 영화소개부분의 영화소개 부분1
		String movie_info_title2 = 영화소개부분의 영화소개 부분2
		--영화소개를 1,2로 나눈 이유는 각 들어가는 css가 다름--
		String movie_info_type = 영화소개부분의 영화 타입
		String movie_info_point = 영화소개에 들어갈그래프의 일종.  데이터가있어야 그래프가 완성됨.*/