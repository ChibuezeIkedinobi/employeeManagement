package com.ikedi.Employee_Managment;

import org.springframework.boot.SpringApplication;

public class TestEmployeeManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmployeeManagmentApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
