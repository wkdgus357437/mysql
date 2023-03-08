package store.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usertable")
//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR"
//				 , sequenceName = "MEMBER_SEQ"
//				 , initialValue = 1
//				 , allocationSize = 1
//)
public class UserDTO {
	@Id
	@Column(name="username")
	private String userName;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
}
