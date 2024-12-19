package mk.finki.ukim.mk.lab1;//package mk.finki.ukim.mk.lab1;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@SpringBootApplication
//@ServletComponentScan
//public class Lab1Application {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Lab1Application.class, args);
//
//
//
//    }
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
//
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@ServletComponentScan
public class Lab1Application{

    public static void main(String [] args){
        SpringApplication.run(Lab1Application.class,args);
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}