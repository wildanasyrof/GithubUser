package com.dicoding.githubuser.api.response

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}