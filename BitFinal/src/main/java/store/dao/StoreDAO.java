package store.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.main.bitfinal.memberService.memberEntity.User;

import store.bean.StoreDTO;
import store.bean.UserDTO;

@Repository
public interface StoreDAO extends JpaRepository<StoreDTO, Long>{
	
	@Query("select storeDTO from StoreDTO storeDTO where storeDTO.subject=:subject")
	public Optional<StoreDTO> findBySubject(@Param("subject") String subject);
	
	@Query("select storeDTO from StoreDTO storeDTO where storeDTO.category=:category")
	public List<StoreDTO> findByPopcorn(@Param("category") String category);
	
	@Query("select storeDTO from StoreDTO storeDTO where storeDTO.store_seq=:store_seq")
	public Optional<StoreDTO> findByStore_seq(@Param("store_seq") int store_seq);
	
	@Query("select userDTO from UserDTO userDTO where userDTO.userName=:userName and userDTO.password=:password")
	public UserDTO login(@Param("userName") String userName, @Param("password") String password);
	
//	@Query("SELECT storeDTO.content, storeDTO.subject, storeDTO.store_seq FROM ( SELECT storeDTO.content, storeDTO.subject, storeDTO.store_seq, storeDTO.img, storeDTO.subsubject, storeDTO.price FROM StoreDTO storeDTO where storeDTO.category='combo' ORDER BY storeDTO.store_seq DESC ) WHERE ROWNUM <= 4")
//	public List<StoreDTO> getIndexCombo();
	
	@Query("SELECT storeDTO FROM StoreDTO storeDTO where storeDTO.category='combo' ORDER BY storeDTO.store_seq DESC")
	public List<StoreDTO> getIndexCombo();

	@Query("SELECT storeDTO FROM StoreDTO storeDTO where storeDTO.category='popcorn' ORDER BY storeDTO.store_seq DESC")
	public List<StoreDTO> getIndexPopcorn();

	@Query("SELECT storeDTO FROM StoreDTO storeDTO where storeDTO.category='drink' ORDER BY storeDTO.store_seq DESC")
	public List<StoreDTO> getIndexDrink();

	@Query("SELECT storeDTO FROM StoreDTO storeDTO where storeDTO.category='snack' ORDER BY storeDTO.store_seq DESC")
	public List<StoreDTO> getIndexSnack();
	
	@Query("select user from User user where user.username=:username")
	public User getUser(@Param("username") String username);

//	관리자 스토어 상품 삭제
	@Transactional
	@Modifying
	@Query(value = "delete from storetable where store_seq=:store_seq",nativeQuery = true)
	public void deleteByAdminStoreDel(@Param("store_seq") String store_seq);

//	관리자 스토어 검색
	@Query("select storeDTO from StoreDTO storeDTO where storeDTO.subject like '%' || :adminStoreSearchKeyword || '%'")
	public List<StoreDTO> getAdminStoreSubject(@Param("adminStoreSearchKeyword") String adminStoreSearchKeyword);

//	관리자 스토어 검색
	
	@Query("select storeDTO from StoreDTO storeDTO where storeDTO.content like '%' || :adminStoreSearchKeyword || '%'")
	public List<StoreDTO> getAdminStoreContent(@Param("adminStoreSearchKeyword") String adminStoreSearchKeyword);

//	관리자 스토어 상품 찾기
	@Query("select storeDTO from StoreDTO storeDTO where storeDTO.store_seq=:store_seq")
//	@Query(value= "select form storetable where store_seq=:store_seq",nativeQuery = true)
	public Optional<StoreDTO> findBygetAdminStoreList(@Param("store_seq") int store_seq);

}
