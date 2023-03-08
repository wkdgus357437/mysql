package com.main.bitfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = {"movie.bean","com.main.bitfinal.memberService.memberEntity","com.adminBoard.bean","user.bean","store.bean"})
@EnableJpaRepositories(basePackages = {"movie.dao", "com.main.bitfinal.memberService.repository","com.adminBoard.dao","user.dao","store.dao"})
@ComponentScan(basePackages = {"com.main.bitfinal","com.adminBoard.*","user.*","store.*", "movielistmain.controller","movielistmaster.controller", "movie.*"})
@SpringBootApplication
@EnableScheduling
public class BitFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitFinalApplication.class, args);

    }

}
