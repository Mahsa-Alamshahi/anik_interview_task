package com.anik.interview_task.ui.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anik.interview_task.common.Result
import com.anik.interview_task.domain.usecase.GenerateVerificationCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val generateVerificationCodeUseCase: GenerateVerificationCodeUseCase
) : ViewModel() {


    private val _generateVerificationCodeState = mutableStateOf(GenerateVerificationCodeState())
    val generateVerificationCodeState: State<GenerateVerificationCodeState> = _generateVerificationCodeState


    fun generateCode() {
        viewModelScope.launch {
            val sendCodeResult = generateVerificationCodeUseCase()

            sendCodeResult.collect { result ->

              when (result) {
                    is Result.Success -> {
                        _generateVerificationCodeState.value =
                            GenerateVerificationCodeState(verificationCodeList = result.data ?: emptyList())
                    }

                    is Result.Loading -> {
                        _generateVerificationCodeState.value = GenerateVerificationCodeState(isLoading = true)
                    }

                    is Result.Error -> {
                        _generateVerificationCodeState.value =
                            GenerateVerificationCodeState(error = result.message ?: "An error accured.")
                    }
                }

            }
        }
    }


}