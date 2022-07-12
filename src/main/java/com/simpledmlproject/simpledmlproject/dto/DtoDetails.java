package com.simpledmlproject.simpledmlproject.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simpledmlproject.simpledmlproject.entity.Details;

@Configuration
public class DtoDetails {
	
	/**
	 * Just for testing the creation of Beans using Java Configuration.
	 * @return {@link Details}
	 * : The 'Details' entity class.
	 */
	@Bean
	public Details getDetailsBean() {
		Details dtl = new Details();
		dtl.setId("1");
		dtl.setName("Suraj");
		dtl.setAddress("Abc efg");
		
		return dtl;
	}
}
