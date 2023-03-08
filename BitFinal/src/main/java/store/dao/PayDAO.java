package store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import store.bean.PayDTO;

import java.util.List;

@Repository
public interface PayDAO extends JpaRepository<PayDTO, String> {
	
	@Query("select payDTO from PayDTO payDTO where payDTO.orderNumber=:ordernumber")
	public PayDTO findByPay(@Param("ordernumber") String orderNumber);

	@Query("select p from PayDTO p where p.userName = ?1 order by p.orderNumber desc ")
	List<PayDTO> findByMyPayment(String username);
}
