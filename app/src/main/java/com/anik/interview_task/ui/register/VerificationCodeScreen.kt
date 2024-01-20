package com.anik.interview_task.ui.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anik.interview_task.R
import com.anik.interview_task.ui.component.EmptyVerificationCodeListView
import com.anik.interview_task.ui.component.ErrorView
import com.anik.interview_task.ui.component.LoadingView
import com.anik.interview_task.ui.component.VerificationCodeView


@Composable
fun VerificationCodeScreenRoute(
    onNavigateBack: () -> Unit
) {

    val viewModel = hiltViewModel<RegisterViewModel>()

    VerificationCodeScreen(
        sendVerificationCodeState = viewModel.generateVerificationCodeState,
        onNavigateBack= onNavigateBack,
        onSendVerificationCode = viewModel::generateCode
    )
}


@Composable
fun VerificationCodeScreen(
    sendVerificationCodeState: State<GenerateVerificationCodeState>,
    onNavigateBack: () -> Unit,
    onSendVerificationCode: () -> Unit
) {


    var sizeImage by remember { mutableStateOf(IntSize.Zero) }


    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = sizeImage.height.toFloat() / 3,
        endY = sizeImage.height.toFloat()
    )


    LaunchedEffect(key1 = Unit) {
        onSendVerificationCode()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {


        Image(
            painter = painterResource(id = R.drawable.main_bg),
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    sizeImage = it.size
                },
            contentScale = ContentScale.FillBounds,
            contentDescription = "Main Background"
        )



        Box(
            modifier = Modifier
                .matchParentSize()
                .background(gradient)
        )


        if (sendVerificationCodeState.value.isLoading) {
            LoadingView()
        } else if (sendVerificationCodeState.value.error.isNotBlank()) {
            ErrorView(errorMessage = sendVerificationCodeState.value.error) {
               onNavigateBack()
            }
        } else if (sendVerificationCodeState.value.verificationCodeList.isEmpty()) {
            EmptyVerificationCodeListView(errorMessage = "Verification code not generated. Please try again.") {
                onNavigateBack()
            }
        } else {


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Card(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(4.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(.5.dp, Color.Gray),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 16.dp
                        )
                    ) {


                        Image(
                            painter = painterResource(id = R.drawable.header_bg),
                            modifier = Modifier
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(12.dp)),
                            contentDescription = "Main Background",
                        )

                    }

                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    VerificationCodeView(sendVerificationCodeState)

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        content = {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Button Icon",
                                tint = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Start", fontSize = 16.sp)
                        }
                    )


                    Spacer(modifier = Modifier.height(32.dp))

                }
            }
        }
    }
}

