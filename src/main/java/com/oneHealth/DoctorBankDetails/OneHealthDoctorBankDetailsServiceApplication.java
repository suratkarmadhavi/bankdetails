package com.oneHealth.DoctorBankDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class OneHealthDoctorBankDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneHealthDoctorBankDetailsServiceApplication.class, args);
	}
	@GetMapping
	public String Welcome() {
		
		return "Welcome From OneHealth Team (OneHealth-DoctorBankDetailsService)!!!";
	}

}
