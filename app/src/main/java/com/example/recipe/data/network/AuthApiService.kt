package com.example.recipe.data.network

import com.example.recipe.common.Constants
import com.example.recipe.data.model.ApiResponse
import com.example.recipe.data.model.AuthRequest
import com.example.recipe.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST(Constants.LOGIN_ENDPOINT)
    suspend fun login(@Body authRequest: AuthRequest): ApiResponse<LoginResponse>
}