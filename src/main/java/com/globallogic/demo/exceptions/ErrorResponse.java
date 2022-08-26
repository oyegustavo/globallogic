package com.globallogic.demo.exceptions;

import java.util.List;

import com.globallogic.demo.dto.ErrorDto;

import lombok.Data;

@Data
public class ErrorResponse
{
    public ErrorResponse(List<ErrorDto> error) {
        super();
        this.error = error;
    }
  
    private List<ErrorDto> error;
 
}
