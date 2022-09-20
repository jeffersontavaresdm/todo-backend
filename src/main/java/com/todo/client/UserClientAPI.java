package com.todo.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserClientAPI {
  private static Retrofit retrofit = null;
  public static final String BASE_URL = "http://localhost:3000/teste";

  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    }

    return retrofit;
  }
}