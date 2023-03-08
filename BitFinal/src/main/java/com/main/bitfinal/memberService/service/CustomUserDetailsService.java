package com.main.bitfinal.memberService.service;

import com.main.bitfinal.memberService.memberEntity.User;
import com.main.bitfinal.memberService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(username + "을 찾을 수 없다!!!"));
    }

    private UserDetails createUserDetails(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRoleType().toString());

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getId()),
                user.getPassword(),
                Collections.singleton(grantedAuthority) // UserDetails 타입 내부의 권한은 컬렉션 타입이고 컬렉션에 싱글톤(단한개의 객체만 저장 가능하다) 으로 리턴
        );
    }
}
// 실행한 loadUserByUsername 은 받은 username 을 통해
// user 가 실제로 존재하는지 알아보는 메소드다. 존재하지 않으면 예외를 날린다.