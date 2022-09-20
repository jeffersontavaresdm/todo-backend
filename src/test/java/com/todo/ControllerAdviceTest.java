//package com.todo;
//
//import org.apache.catalina.connector.Response;
//import org.junit.jupiter.api.Test;
//
//public class ControllerAdviceTest {
//
//  @Test
//  public void whenMethodArgumentMismatch_thenBadRequest() {
//    Response response = givenAuth().get(URL_PREFIX + "/api/foos/ccc");
//    ApiError error = response.as(ApiError.class);
//
//    assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
//    assertEquals(1, error.getErrors().size());
//    assertTrue(error.getErrors().get(0).contains("should be of type"));
//  }
//}