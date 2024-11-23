package com.ikedi.Employee_Managment.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String email;
    private String role;
    private double salary;
}
