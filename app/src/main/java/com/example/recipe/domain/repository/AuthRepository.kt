package com.example.recipe.domain.repository

import com.example.recipe.data.model.AuthRequest
import com.example.recipe.data.model.LoginResponse
import com.example.recipe.domain.util.Result

interface AuthRepository {
    suspend fun login(authRequest: AuthRequest): Result<LoginResponse>
}