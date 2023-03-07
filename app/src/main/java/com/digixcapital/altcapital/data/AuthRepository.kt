package com.digixcapital.altcapital.data

import com.digixcapital.altcapital.utils.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun loginUser(email:String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email:String, password: String): Flow<Resource<AuthResult>>

    fun googleLoginUser(credential: AuthCredential): Flow<Resource<AuthResult>>
}
