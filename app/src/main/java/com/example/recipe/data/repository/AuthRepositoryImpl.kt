package com.example.recipe.data.repository

import com.example.recipe.data.model.AuthRequest
import com.example.recipe.data.model.LoginResponse
import com.example.recipe.data.network.AuthApiService
import com.example.recipe.domain.repository.AuthRepository
import com.example.recipe.domain.util.Result
import java.io.IOException
import retrofit2.HttpException

class AuthRepositoryImpl(private val authApiService: AuthApiService) : AuthRepository {
    override suspend fun login(authRequest: AuthRequest): Result<LoginResponse> {
        return try {
            val response = authApiService.login(authRequest)
            if (response.status == 200) {
                Result.Success(response.data)
            } else {
                Result.Error(response.message)
            }
        } catch (e: HttpException) {
            Result.Error("An unexpected error occurred")
        } catch (e: IOException) {
            Result.Error("Couldn't reach server. Check your internet connection.")
        }
    }
}