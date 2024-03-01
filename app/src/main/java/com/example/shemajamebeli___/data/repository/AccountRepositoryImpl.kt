package com.example.shemajamebeli___.data.repository

import com.example.shemajamebeli___.data.common.HandleResource
import com.example.shemajamebeli___.data.common.Resource
import com.example.shemajamebeli___.data.common.resourceMapper
import com.example.shemajamebeli___.data.mapper.toDomain
import com.example.shemajamebeli___.data.model.FromAccountDto
import com.example.shemajamebeli___.data.service.AccountService
import com.example.shemajamebeli___.domain.model.FromAccountModel
import com.example.shemajamebeli___.domain.model.ToAccountModel
import com.example.shemajamebeli___.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountService: AccountService,
    private val handleResource: HandleResource,
) : AccountRepository {
    override suspend fun getFromAccountList(): Flow<Resource<List<FromAccountModel>>> =
        handleResource.handleResource {
            accountService.getFromAccount()
        }.map { resource ->
            resource.resourceMapper { accountDtoList ->
                accountDtoList.map {
                    it.toDomain()
                }
            }
        }

    override suspend fun getToAccountByNumber(accountNum: String): Flow<Resource<ToAccountModel>> =
        handleResource.handleResource {
            accountService.getToAccount(accountNumber = accountNum)
        }.map {
            it.resourceMapper { resource ->
                resource.toDomain()
            }
        }
}