package com.globallogic.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecordNotFoundException extends RuntimeException
{
  private static final long serialVersionUID = 1L;
 
  public RecordNotFoundException(String message) {
        super(message);
    }
}
