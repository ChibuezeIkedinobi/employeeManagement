package com.ikedi.Employee_Managment.service;

import com.ikedi.Employee_Managment.payload.request.AttendanceRequestDto;
import com.ikedi.Employee_Managment.payload.request.EmployeeRequestDto;
import com.ikedi.Employee_Managment.payload.response.ApiResponse;
import com.ikedi.Employee_Managment.payload.response.AttendanceResponseDto;
import com.ikedi.Employee_Managment.payload.response.EmployeeResponseDto;

import java.util.List;

public interface AdminService {

    ApiResponse <EmployeeResponseDto> createEmployee(EmployeeRequestDto employee);

    ApiResponse <List<EmployeeResponseDto>> getAllEmployee();

    ApiResponse <AttendanceResponseDto> markAttendance(AttendanceRequestDto attendance);

    ApiResponse <List<AttendanceResponseDto>> getAllAttendance();

    void deleteEmployee(Long employeeId);
}
