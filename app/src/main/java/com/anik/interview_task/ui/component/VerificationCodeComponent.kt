package com.anik.interview_task.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anik.interview_task.ui.register.GenerateVerificationCodeState


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerificationCodeView(verificationCodeList: State<GenerateVerificationCodeState>) {


    var firstDigit by remember { mutableStateOf(verificationCodeList.value.verificationCodeList[0].toString()) }
    var secondDigit by remember { mutableStateOf(verificationCodeList.value.verificationCodeList[1].toString()) }
    var thirdDigit by remember { mutableStateOf(verificationCodeList.value.verificationCodeList[2].toString()) }
    var forthDigit by remember { mutableStateOf(verificationCodeList.value.verificationCodeList[3].toString()) }


    val focusManager = LocalFocusManager.current
    val (first, second, third, forth) = FocusRequester.createRefs()


    val textStyle =
        TextStyle(color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 22.sp)

    val textColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        cursorColor = Color.White,
        focusedBorderColor = Color.White,
        unfocusedBorderColor = MaterialTheme.colorScheme.primary
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(all = 12.dp)
                .focusRequester(first)
                .focusProperties {
                    next = second
                },
            textStyle = textStyle,
            colors = textColors,
            value = firstDigit,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                firstDigit = it
                if (it.length == 1) {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            },
        )


        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(all = 12.dp)
                .focusRequester(second)
                .focusProperties {
                    next = third
                    previous = first
                },
            textStyle = textStyle,
            colors = textColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = secondDigit,
            onValueChange = {
                secondDigit = it
                if (it.length == 1) {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            },
        )


        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(all = 12.dp)
                .focusRequester(third)
                .focusProperties {
                    next = forth
                    previous = second
                },
            textStyle = textStyle,
            colors = textColors,
            value = thirdDigit,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                thirdDigit = it
                if (it.length == 1) {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            },
        )


        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(all = 12.dp)
                .focusRequester(forth)
                .focusProperties {
                    previous = third
                },
            textStyle = textStyle,
            colors = textColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = forthDigit,
            onValueChange = {
                forthDigit = it
            },
        )
    }

}