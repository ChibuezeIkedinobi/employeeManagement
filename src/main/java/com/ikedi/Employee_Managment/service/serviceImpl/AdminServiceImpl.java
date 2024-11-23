package com.ikedi.Employee_Managment.service.serviceImpl;

import com.ikedi.Employee_Managment.enums.Absent;
import com.ikedi.Employee_Managment.enums.Roles;
import com.ikedi.Employee_Managment.model.Attendance;
import com.ikedi.Employee_Managment.model.Employee;
import com.ikedi.Employee_Managment.payload.request.AttendanceRequestDto;
import com.ikedi.Employee_Managment.payload.request.EmployeeRequestDto;
import com.ikedi.Employee_Managment.payload.response.ApiResponse;
import com.ikedi.Employee_Managment.payload.response.AttendanceResponseDto;
import com.ikedi.Employee_Managment.payload.response.EmployeeResponseDto;
import com.ikedi.Employee_Managment.repository.AttendanceRepository;
import com.ikedi.Employee_Managment.repository.EmployeeRepository;
import com.ikedi.Employee_Managment.service.AdminService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;


    @Override
    public ApiResponse<EmployeeResponseDto> createEmployee(EmployeeRequestDto employeeRequest) {
        if (employeeRepository.existsByEmail(employeeRequest.getEmail())) {
            throw new RuntimeException("Email already Exists");
        }

        if (employeeRequest.getName().equalsIgnoreCase("admin")) {
            throw new RuntimeException("There can only be one Admin... Enter another name");
        }

        Employee employee = Employee.builder()
                .name(employeeRequest.getName())
                .email(employeeRequest.getEmail())
                .password(employeeRequest.getPassword())
                .role(Roles.USER)
                .salary(employeeRequest.getSalary()).build();
        employeeRepository.save(employee);

        EmployeeResponseDto responseDto = EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .email(employee.getEmail())
                .role(Roles.USER.name())
                .salary(employee.getSalary())
                .build();

        return new ApiResponse<>("Employee created", responseDto);
    }

    @Override
    public ApiResponse<List<EmployeeResponseDto>> getAllEmployee() {
        List<EmployeeResponseDto> allEmployee = employeeRepository.findAll()
                .stream()
                .map(employee -> EmployeeResponseDto.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .email(employee.getEmail())
                        .role(employee.getRole().name())
                        .salary(employee.getSalary())
                        .build()).toList();
        return new ApiResponse<>("Employees retrieved", allEmployee);
    }

    @Override
    public ApiResponse<AttendanceResponseDto> markAttendance(AttendanceRequestDto attendanceRequest) {
        attendanceRequest.validate();

        Employee employee = employeeRepository.findById(attendanceRequest.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LocalTime clockIn = attendanceRequest.getAbsent() == Absent.YES ? null : attendanceRequest.getClockIn();
        LocalTime clockOut = attendanceRequest.getAbsent() == Absent.YES ? null : attendanceRequest.getClockOut();

        Attendance attendance = Attendance.builder()
                .employee(employee)
                .clockIn(clockIn)
                .clockOut(clockOut)
                .present(attendanceRequest.getPresent())
                .absent(attendanceRequest.getAbsent())
                .reason(attendanceRequest.getReason())
                .build();
        attendanceRepository.save(attendance);

        AttendanceResponseDto attendanceResponse = AttendanceResponseDto.builder()
                .id(attendance.getId())
                .employeeId(employee.getId())
                .employeeName(employee.getName())
                .clockIn(attendance.getClockIn())
                .clockOut(attendance.getClockOut())
                .present(attendance.getPresent())
                .absent(attendance.getAbsent())
                .reason(attendance.getReason())
                .build();
        return new ApiResponse<>("Attendance Marked Successfully", attendanceResponse);
    }

    @Override
    public ApiResponse<List<AttendanceResponseDto>> getAllAttendance() {
        List<AttendanceResponseDto> attendanceList = attendanceRepository.findAll()
                .stream()
                .map(attendance -> AttendanceResponseDto.builder()
                        .id(attendance.getId())
                        .employeeId(attendance.getEmployee().getId())
                        .employeeName(attendance.getEmployee().getName())
                        .clockIn(attendance.getClockIn())
                        .clockOut(attendance.getClockOut())
                        .present(attendance.getPresent())
                        .absent(attendance.getAbsent())
                        .reason(attendance.getReason())
                        .build())
                .toList();

        return new ApiResponse<>("Attendance Retrieved", attendanceList);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));
        employeeRepository.delete(employee);
    }
}
