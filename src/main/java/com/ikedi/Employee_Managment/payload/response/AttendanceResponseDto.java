package com.ikedi.Employee_Managment.payload.response;

import com.ikedi.Employee_Managment.enums.Absent;
import com.ikedi.Employee_Managment.enums.Present;
import com.ikedi.Employee_Managment.enums.Reasons;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponseDto {

    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private Present present;
    private Absent absent;
    private Reasons reason;
}
