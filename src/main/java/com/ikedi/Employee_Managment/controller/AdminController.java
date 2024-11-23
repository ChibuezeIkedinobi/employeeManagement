package com.ikedi.Employee_Managment.controller;

import com.ikedi.Employee_Managment.payload.request.AttendanceRequestDto;
import com.ikedi.Employee_Managment.payload.request.EmployeeRequestDto;
import com.ikedi.Employee_Managment.payload.response.ApiResponse;
import com.ikedi.Employee_Managment.payload.response.AttendanceResponseDto;
import com.ikedi.Employee_Managment.payload.response.EmployeeResponseDto;
import com.ikedi.Employee_Managment.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse<EmployeeResponseDto>> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.ok(adminService.createEmployee(employeeRequestDto));
    }

    @GetMapping("/employees")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDto>>> getAllEmployees() {
        return ResponseEntity.ok(adminService.getAllEmployee());
    }

    @PostMapping("/attendance")
    public ResponseEntity<ApiResponse> markAttendance(@RequestBody AttendanceRequestDto attendanceRequestDto) {
        return ResponseEntity.ok(adminService.markAttendance(attendanceRequestDto));
    }

    @GetMapping("/attendance")
    public ResponseEntity<ApiResponse<List<AttendanceResponseDto>>> getAllAttendance() {
        return ResponseEntity.ok(adminService.getAllAttendance());  // kxWxlp*y_6ZZ
    }

}
