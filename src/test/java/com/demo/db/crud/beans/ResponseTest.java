package com.demo.db.crud.beans;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {Response.class})
@ExtendWith(SpringExtension.class)
public class ResponseTest {

    @Test
    public void constructor() {
        Response response = new Response();
        response.setStatusCode(200);
        response.setStatusMessage("Success");
        response.setResult("Result");
        assertEquals(200, response.getStatusCode());
        assertEquals("Success", response.getStatusMessage());
        assertEquals("Result", response.getResult());
    }

    @Test
    public void constructor1() {
        Response response = new Response(200, "Success", "Result");
        assertEquals(200, response.getStatusCode());
        assertEquals("Success", response.getStatusMessage());
        assertEquals("Result", response.getResult());
    }
}
