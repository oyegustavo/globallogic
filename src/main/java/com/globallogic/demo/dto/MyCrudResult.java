package com.globallogic.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyCrudResult {
    private boolean success;
    private Object data;
    private String message;
	
    public void setAllFieldsCrudResult(boolean success, Object data, String message) {		
		this.success = success;
		this.data = data;
		this.message = message;
	}
    
}
