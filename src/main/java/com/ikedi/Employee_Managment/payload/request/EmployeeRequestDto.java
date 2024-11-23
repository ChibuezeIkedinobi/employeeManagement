package com.ikedi.Employee_Managment.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {

    private String name;
    private String email;
    private String password;
    private double salary;
}
