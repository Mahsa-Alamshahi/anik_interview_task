package com.anik.interview_task.ui.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anik.interview_task.R
import com.anik.interview_task.common.AppConstants.PHONE_NUMBER_TEXT_LENGTH


@Composable
fun PhoneNumberScreenRoute(
    onRegisterClick: () -> Unit
) {

    PhoneNumberScreen(
        onRegisterClick = onRegisterClick
    )
}


@Composable
fun PhoneNumberScreen(
    onRegisterClick: () -> Unit
) {


    var phoneNumberText by remember { mutableStateOf("") }
    var imageSize by remember { mutableStateOf(IntSize.Zero) }
    var isError by rememberSaveable { mutableStateOf(false) }

val configuration = LocalConfiguration.current
    val widthInDp = configuration.screenWidthDp.dp

    fun validatePhoneNumber(text: String) {
        isError = text.length < PHONE_NUMBER_TEXT_LENGTH
    }


    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = imageSize.height.toFloat() / 3,
        endY = imageSize.height.toFloat()
    )


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
                    imageSize = it.size
                },
            contentScale = ContentScale.FillBounds,
            contentDescription = "Main Background"
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(gradient)
        )



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
                            .width(widthInDp/3)
                            .clip(RoundedCornerShape(12.dp)),
                        contentDescription = "Header Image",
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


                OutlinedTextField(
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    value = phoneNumberText,
                    onValueChange = {
                        phoneNumberText = it
                    },
                    label = {
                        Text(
                            "Enter phone number", color = Color.White, fontSize = 16.sp
                        )
                    },
                    singleLine = true,
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Length: ${phoneNumberText.length}/$PHONE_NUMBER_TEXT_LENGTH",
                                color = Color.Red
                            )
                        }
                    },
                    trailingIcon = {
                        if (isError) Icon(Icons.Filled.Info, "Error", tint = Color.Red)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
                        .border(
                            width = .5.dp,
                            shape = MaterialTheme.shapes.extraSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Phone Icon",
                            tint = Color.White
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        cursorColor = Color.White,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )


                Button(onClick = {
                    validatePhoneNumber(phoneNumberText)
                    if (isError.not()) {
                        onRegisterClick()
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    content = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Button Icon",
                            tint = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Register", fontSize = 16.sp)
                    })


                Spacer(modifier = Modifier.height(32.dp))

            }
        }

    }


}


@Composable
@Preview
fun PreviewPhoneNumberScreen() {
    PhoneNumberScreen(){}
}