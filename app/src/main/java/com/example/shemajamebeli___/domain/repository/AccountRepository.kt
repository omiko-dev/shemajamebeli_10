package com.example.shemajamebeli___.domain.repository

import com.example.shemajamebeli___.data.common.Resource
import com.example.shemajamebeli___.domain.model.FromAccountModel
import com.example.shemajamebeli___.domain.model.ToAccountModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getFromAccountList(): Flow<Resource<List<FromAccountModel>>>

    suspend fun getToAccountByNumber(accountNum: String): Flow<Resource<ToAccountModel>>
}