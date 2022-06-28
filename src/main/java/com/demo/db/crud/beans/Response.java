package com.demo.db.crud.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private int statusCode;
    private String statusMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object result;
}
