package com.main.bitfinal.memberService.memberController;

import com.main.bitfinal.memberService.dto.ChangePasswordRequestDTO;
import com.main.bitfinal.memberService.dto.UserRequestDTO;
import com.main.bitfinal.memberService.dto.UserResponseDTO;
import com.main.bitfinal.memberService.memberEntity.RoleType;
import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.repository.UserRepository;
import com.main.bitfinal.memberService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor // 생성자 자동 생성 by 롬복 (autowired 필요없음)
@RequestMapping("/member")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/nickname")
    public ResponseEntity<UserResponseDTO> setMemberNickname(@RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(userService.changeUsername(request.getUsername(), request.getName()));
    }

    @PostMapping("/password")
    public ResponseEntity<UserResponseDTO> setMemberPassword(@RequestBody ChangePasswordRequestDTO request) {
        return ResponseEntity.ok(userService.changeMemberPassword(request.getExPwd(), request.getNewPwd()));
    }

    @GetMapping(path = "delete")
    public void deleteUser(@RequestParam String username) {
        System.out.println(username);
        userService.deleteByUsername(username);
    }

    @GetMapping(path = "roleChange")
    public String roleChange(@RequestParam String username,@RequestParam String roleType) {
        User user = userRepository.findByUsername2(username);
        if(user.getRoleType().equals(roleType)){
            return "equal";
        }else{
            if(roleType.equals("ROLE_ADMIN")){
                user.setRoleType(RoleType.ROLE_ADMIN);
            }else{
                user.setRoleType(RoleType.ROLE_USER);
            }
            userRepository.save(user);
            return"changed";
        }
    }

    // 아이디 중복체크
    @GetMapping(path = "duplicationChk")
    public String duplicationChk(@RequestParam String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return "duplicate";
        } else {
            return null;
        }
    }

    // 이미 가입한 회원 체크 (회원가입 시 본인인증 후 이름 중복검사)
    @GetMapping(path = "existName")
    public String existName(@RequestParam String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return "exist";
        } else {
            return null;
        }
    }

    @GetMapping(path = "existName2")
    public String existName2(@RequestParam String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return "exist";
        } else {
            return null;
        }
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"}) // 로그인 정보없으면 401 에러 후 로그인페이지로 유턴 시킴
    @GetMapping("/me") // axios 요청 시 헤더에 토큰 담기
    public ResponseEntity<UserResponseDTO> getMyMemberInfo() {

        UserResponseDTO myInfoBySecurity = userService.getMyInfoBySecurity();

        System.out.println("유저이름 : " + myInfoBySecurity.getName());

        return ResponseEntity.ok((myInfoBySecurity));
    }

    // 아이디 찾기
    @GetMapping("findUsername")
    public String findUsername(@ModelAttribute User user) {
        String name = user.getName();
        String birth = user.getBirth();
        String phoneNumber = user.getPhoneNumber();
        return userRepository.findByMyId(name, birth, phoneNumber);
    }

    // 비번찾기 입력정보 확인 후 true or false
    @GetMapping("findPassword")
    public String findPassword(@ModelAttribute User user) {
        String username = user.getUsername();
        String name = user.getName();
        String birth = user.getBirth();
        String phoneNumber = user.getPhoneNumber();

        Optional<User> userChk = userRepository.findByPasswordUserInfo(username, name, birth, phoneNumber);
        if (userChk.isPresent()) {
            return "true";
        } else
            return null;
    }

    // 비밀번호 찾기 후 새로운 비밀번호 저장
    @Transactional
    @PostMapping("findAndChangePassword")
    public void findAndChangePassword(@ModelAttribute User user) {
        String username = user.getUsername();
        String rawPassword = user.getPassword();
        System.out.println(username + rawPassword);
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        String password = user.getPassword();
        userRepository.changePassword(username, password);
    }

    // 관리자 페이지 유저리스트
    @Secured("ROLE_ADMIN") // 관리자만 볼 수 있다, 권한없으면 로그아웃 시킴
    @GetMapping(path = "getUserList")
    public ResponseEntity<List<User>> getAllMember() {
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }

    // 개인정보 수정 페이지(기존 비밀번호 일치 확인)
    @PostMapping(path = "exPwdChk")
    public String exPwdChk(@ModelAttribute User user) {
        String username = user.getUsername(); // 리액트에서 넘어온 것
        String password = user.getPassword(); // 리액트에서 넘어온 것
        String exPwds = userRepository.findByPassword(username); // DB 조회

        if (passwordEncoder.matches(password, exPwds)) {
            return "correct";
        } else {
            return "false";
        }
    }
}

// ResponseEntity 구조 http://stanley-dev-log.tistory.com/7
