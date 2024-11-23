package com.ikedi.Employee_Managment.payload.request;

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
public class AttendanceRequestDto {

    private Long employeeId;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private Present present;
    private Absent absent;
    private Reasons reason;

    public void validate() {
        if (absent == Absent.NO && (clockIn == null || clockOut == null)) {
            throw new ValidationException("ClockIn and ClockOut must be provided if the employee is present");
        }
    }
}
