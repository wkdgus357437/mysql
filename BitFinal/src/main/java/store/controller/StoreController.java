package store.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.bitfinal.memberService.dto.UserRequestDTO;
import com.main.bitfinal.memberService.memberEntity.User;

import movie.bean.MovieDTO;

import javax.servlet.http.HttpSession;
import store.bean.CartDTO;
import store.bean.PayDTO;
import store.bean.Request;
import store.bean.SmsResponse;
import store.bean.StoreDTO;
import store.bean.UserDTO;
import store.dao.PayDAO;
import store.service.SmsService;
import store.service.StoreService;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@CrossOrigin
@RestController //@RestController = @Controller + @ResponseBody
@RequestMapping(value = "store")
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private SmsService smsService;

	@Autowired
	private PayDAO payDAO;

	@GetMapping(path = "getStoreList")
	public List<StoreDTO> getStoreList() {
		return storeService.getStoreList();
	}

	@GetMapping(path = "getPopcornList")
	public List<StoreDTO> getPopcornList(@RequestParam String category) {
		return storeService.getPopcornList(category);
	}

	@PostMapping(path = "write")
	public void write(@ModelAttribute StoreDTO storeDTO) {
		storeService.write(storeDTO);
	}

	@GetMapping(path = "isExistSubject")
	public String isExistSubject(@RequestParam String subject) {
		return storeService.isExistSubject(subject);
	}

	@GetMapping(path = "getStore")
	public Optional<StoreDTO> getStore(@RequestParam int store_seq) {
		return storeService.getStore(store_seq);
	}

	// name="img" 1개 이상일 경우
	   @PostMapping(value = "/imgUpload")
	   @ResponseBody
	   public void upload(@RequestParam MultipartFile img) {
	      //실제폴더
	      // String filePath = session.getServletContext().getRealPath("/public/storage");
	      // System.out.println("실제폴더 : " + filePath);
	      //가상폴더 (filePath = 각자의 스토리지 폴더로 지정해야 상품 올라감
	      String filePath = "/Users/jojanghyeon/BitCamp_FinalPriject/workspace/BitFinal/src/webapp/public/storage";
	      String fileName = img.getOriginalFilename();

	      File file = new File(filePath, fileName);

	      try {
	         //FileCopyUtils.copy(upload.getInputStream(), new FileOutputStream(file)); // 가상폴더로 복사한다
	         img.transferTo(file);
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }//복사
	   }

	//usertable 이용
	@PostMapping(path = "login")
	public UserDTO login(@ModelAttribute UserDTO userDTO) {
		return storeService.login(userDTO);
	}

	@PostMapping(path = "getUser")
	public User getUser(@RequestParam String username) {
		return storeService.getUser(username);
	}

	//carttable 이용
	@PostMapping(path = "insertCart")
	public void insertCart(@ModelAttribute CartDTO cartDTO) {
		storeService.insertCart(cartDTO);
	}

	@GetMapping(path = "isExistCart")
	public String isExistCart(@RequestParam String userName, int store_seq) {
		return storeService.isExistCart(userName, store_seq);
	}

	@GetMapping(path = "getCartList")
	public List<CartDTO> getCartList(@RequestParam String userName) {
		return storeService.getCartList(userName);
	}

	@GetMapping(path = "getCartListCount")
	public List<Integer> getCartListCount(@RequestParam String userName) {
		return storeService.getCartListCount(userName);
	}

	@GetMapping(path = "deleteCart")
	public void deleteCart(@RequestParam int cart_seq) {
		storeService.deleteCart(cart_seq);
	}

	@PostMapping(path = "updateCart")
	public void updateCart(@ModelAttribute CartDTO cartDTO) {
		System.out.println(cartDTO.getCart_seq());
		storeService.updateCart(cartDTO);
	}

	@GetMapping(path = "getOne")
	public CartDTO getOne(@RequestParam String userName, int store_seq) {
		return storeService.getOne(userName, store_seq);
	}

	@GetMapping(path = "getIndexCombo")
	public List<StoreDTO> getIndexCombo() {
		return storeService.getIndexCombo();
	}

	@GetMapping(path = "getIndexPopcorn")
	public List<StoreDTO> getIndexPopcorn() {
		return storeService.getIndexPopcorn();
	}

	@GetMapping(path = "getIndexDrink")
	public List<StoreDTO> getIndexDrink() {
		return storeService.getIndexDrink();
	}

	@GetMapping(path = "getIndexSnack")
	public List<StoreDTO> getIndexSnack() {
		return storeService.getIndexSnack();
	}

	@PostMapping(path = "insertPay")
	public void insertPay(@ModelAttribute PayDTO payDTO) {

		storeService.insertPay(payDTO);
	}

	@GetMapping(path = "getPay")
	public PayDTO getPay(@RequestParam String orderNumber) {
		System.out.println(orderNumber);
		return storeService.getPay(orderNumber);
	}

	@PostMapping(path = "sms")
	public ResponseEntity<SmsResponse> test(@ModelAttribute Request request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
		System.out.println("controller : " + request.getRecipientPhoneNumber());
		SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
		return ResponseEntity.ok().body(data);
	}

	// 마이페이지 구매현황 가져오기
	@GetMapping(path = "myStorePaymentInfo")
	public List<PayDTO> myStorePaymentInfo(@RequestParam String username) {
		return payDAO.findByMyPayment(username);
	}
	
	// 관리자 store 상품 삭제 추가함 
	@DeleteMapping(path = "adminStoreDel")
	public void adminStoreDel(@RequestParam String store_seq) {
		storeService.adminStoreDel(store_seq);
	}
	
	//  관리자 store 상품 검색 추가함  
	@GetMapping(path = "adminStoreSearch")
	public List<StoreDTO> adminStoreSearch(@RequestParam Map<String, String> map){
		return storeService.adminStoreSearch(map);
	}
	
//	관리자 store 수정 전 데이터 불러오기
	@GetMapping(path="getAdminStoreList")
	public Optional <StoreDTO> getAdminStoreList(@RequestParam int store_seq){
		return storeService.getAdminStoreList(store_seq);
	}

//	관리자 store 상품 수정
	@PutMapping(path="storeUpdate")
	public void storeUpdate(@ModelAttribute StoreDTO storeDTO) {
		storeService.storeUpdate(storeDTO);
	}
	
}
	
