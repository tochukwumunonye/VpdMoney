package com.example.vpdmoney.domain.repository

import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface UsersRepository {

  //uuuuuuu
    suspend fun getUsersFromServer(): Flow<List<UsersDtoItem>>
}

