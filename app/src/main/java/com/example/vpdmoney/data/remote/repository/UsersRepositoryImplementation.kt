package com.example.vpdmoney.data.remote.repository

import com.example.vpdmoney.data.remote.api.APIService
import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import com.example.vpdmoney.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImplementation @Inject constructor(
    private val api: APIService
): UsersRepository{

    override suspend fun getUsersFromServer(): Flow<List<UsersDtoItem>> = flow {
        val userList = api.getUsers()
        emit(userList)
    }
}

/**
2022-09-16 12:11:55.947 24912-24941/com.example.vpdmoney I/okhttp.OkHttpClient:
<-- HTTP FAILED: java.net.SocketTimeoutException:
failed to connect to jsonplaceholder.typicode.com/188.114.97.5 (port 443)
from /10.200.116.57 (port 42900) after 300000ms: isConnected failed: ETIMEDOUT (Connection timed out)
 **/
