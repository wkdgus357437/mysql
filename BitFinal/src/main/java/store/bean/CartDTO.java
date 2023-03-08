package store.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "carttable")
@SequenceGenerator(name = "CART_SEQ_GENERATOR"
				 , sequenceName = "CART_SEQ"
				 , initialValue = 1
				 , allocationSize = 1
)
public class CartDTO {
	@Id //@Id로 설정하면 primary key로 설정된다
	//@GeneratedValue(strategy = GenerationType.AUTO) //특정 데이터베이스에 맞게 자동으로 생성하는 방식, 자동으로 시퀀스 테이블이 생성된다
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CART_SEQ_GENERATOR")
	@Column(name="cart_seq")
	private int cart_seq;
	
	@Column(name="count", nullable = false, length = 30)
	private int count;
	
	@Column(name="store_seq", nullable = false, length = 30)
	private int store_seq;
	
	@Column(name="subject", nullable = false, length = 30)
	private String subject;
	
	@Column(name="subsubject", nullable = false, length = 30)
	private String subSubject;
	
	@Column(name="price", nullable = false, length = 30)
	private String price;
	
	@Column(name="img")
	private String img;
	
	@Column(name="username", nullable = false, length = 30)
	private String userName;
	
	@Column(name = "state")
	private String state;
}
