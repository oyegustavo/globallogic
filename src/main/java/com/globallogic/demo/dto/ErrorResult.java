package com.globallogic.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResult {
    private List<Object> error;
	
    public ErrorResult(Object error) {		
    	List<Object> errors= new ArrayList<Object>();
    	errors.add(error);
    	error= errors;
	}
}