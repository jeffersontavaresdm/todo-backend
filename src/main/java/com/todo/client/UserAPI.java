package com.todo.client;

import com.todo.entity.dto.UserPayload;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {

  @FormUrlEncoded
  @POST("/authentication/token")
  void sendUserWithToken(
    @Field("username") String username,
    @Field("email") String email,
    Callback<UserPayload> callback
  );
}