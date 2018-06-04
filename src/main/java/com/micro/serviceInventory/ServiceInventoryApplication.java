package com.micro.serviceInventory;

import com.micro.serviceInventory.model.Role;
import com.micro.serviceInventory.model.User;
import com.micro.serviceInventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
// uncomment if you want to use Spring XML Configuration
// @ImportResource("classpath:spring-security-config.xml")
public class ServiceInventoryApplication {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init(){
		User user = new User(
		        "memory",
                passwordEncoder.encode("password"),
				"Sajith",
				"Nalaka",

				Arrays.asList(
						new Role("ROLE_USER"),
						new Role("ROLE_ADMIN")));

		if (userRepository.findByUserName(user.getUserName()) == null){
			userRepository.save(user);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceInventoryApplication.class, args);
	}
}
