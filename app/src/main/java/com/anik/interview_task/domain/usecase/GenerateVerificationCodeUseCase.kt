package com.anik.interview_task.domain.usecase

import com.anik.interview_task.common.AppConstants.VERIFICATION_CODE_LENGTH
import com.anik.interview_task.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

class GenerateVerificationCodeUseCase @Inject constructor() {


    suspend operator fun invoke(): Flow<Result<List<Int>>> = flow {

        try {
            emit(Result.Loading())
            val randomNumberList =  List(VERIFICATION_CODE_LENGTH) { Random.nextInt(0, 10) }
            emit(Result.Success(randomNumberList))

        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }




}