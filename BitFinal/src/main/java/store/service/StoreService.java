package store.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.main.bitfinal.memberService.memberEntity.User;

import store.bean.CartDTO;
import store.bean.PayDTO;
import store.bean.StoreDTO;
import store.bean.UserDTO;

public interface StoreService {

	public List<StoreDTO> getStoreList();

	public void write(StoreDTO storeDTO);

	public String isExistSubject(String subject);

	public Optional<StoreDTO> getStore(int store_seq);
	
	public List<StoreDTO> getPopcornList(String category);

	public UserDTO login(UserDTO userDTO);
	
	public User getUser(String username);

	public void insertCart(CartDTO cartDTO);

	public String isExistCart(String userName, int store_seq);

	public List<CartDTO> getCartList(String userName);

	public List<Integer> getCartListCount(String userName);

	public void deleteCart(int cart_seq);

	public void updateCart(CartDTO cartDTO);
	
	public CartDTO getOne(String userName, int store_seq);

	public List<StoreDTO> getIndexCombo();
	
	public List<StoreDTO> getIndexPopcorn();

	public List<StoreDTO> getIndexDrink();

	public List<StoreDTO> getIndexSnack();

	public void insertPay(PayDTO payDTO);

	public PayDTO getPay(String orderNumber);

	//관리자 스토어 상품 삭제
	public void adminStoreDel(String store_seq);
	
	//관리자 스토어 상품 검색	
	public List<StoreDTO> adminStoreSearch(Map<String, String> map);

	//관리자 store 수정 전 데이터 불러오기
	public Optional<StoreDTO> getAdminStoreList(int store_seq);

	//	관리자 store 상품 수정
	public void storeUpdate(StoreDTO storeDTO);


}
