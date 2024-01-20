package com.anik.interview_task.ui.register

import androidx.compose.runtime.Stable


@Stable
data class GenerateVerificationCodeState(
    val isLoading: Boolean = false,
    var verificationCodeList: List<Int> = emptyList(),
    val error: String = ""
)
