package com.moviereview.moviereview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import com.moviereview.moviereview.service.UserDetailsServiceImpl;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MoviereviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviereviewApplication.class, args);
	}

}
