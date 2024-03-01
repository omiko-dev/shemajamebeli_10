package com.example.shemajamebeli___.domain.usecase

import com.example.shemajamebeli___.domain.repository.AccountRepository
import javax.inject.Inject

class GetFromAccountList @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() = accountRepository.getFromAccountList()
}