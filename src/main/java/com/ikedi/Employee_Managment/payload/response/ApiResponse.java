package com.ikedi.Employee_Managment.payload.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse <T> {

    private String Message;
    private T Data;

    public ApiResponse(String message, T data) {
        Message = message;
        Data = data;
    }
}
