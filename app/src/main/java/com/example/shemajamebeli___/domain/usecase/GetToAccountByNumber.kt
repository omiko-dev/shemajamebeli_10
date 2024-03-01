package com.example.shemajamebeli___.domain.usecase

import com.example.shemajamebeli___.domain.repository.AccountRepository
import javax.inject.Inject

class GetToAccountByNumber @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(accountNumber: String) = accountRepository.getToAccountByNumber(accountNum = accountNumber)
}