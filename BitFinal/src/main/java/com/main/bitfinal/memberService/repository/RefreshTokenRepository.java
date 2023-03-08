package com.main.bitfinal.memberService.repository;

import com.main.bitfinal.memberService.memberEntity.RefreshTokenDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenDTO, Long> {

    // username 값으로 토큰을 가져오기 위해 findByKey 추가
    Optional<RefreshTokenDTO> findByKey(String key);

}
