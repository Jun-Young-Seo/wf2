package kr.ac.hansung.cse.hellospringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HelloSpringDataJpaApplication {

    public static void main(String[] args) {
//        String pwd = "123";
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoded = encoder.encode(pwd);
//        System.out.println(encoded);
        SpringApplication.run(HelloSpringDataJpaApplication.class, args);
    }

}
