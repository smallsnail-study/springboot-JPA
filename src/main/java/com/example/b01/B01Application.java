package com.example.b01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // @AuditingEntityListener를 활성화 시키기 위해 추가
public class B01Application {

    public static void main(String[] args) {
        SpringApplication.run(B01Application.class, args);
    }

}
