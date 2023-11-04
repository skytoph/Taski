package com.github.skytoph.taski.presentation.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.skytoph.taski.R
import com.github.skytoph.taski.ui.components.BasicTextField
import com.github.skytoph.taski.ui.components.ErrorText
import com.github.skytoph.taski.ui.components.PasswordField

@Composable
fun SignInScreen(viewModel: SignInViewModel, onSignIn: () -> Unit) {
    val state = viewModel.state()

    LaunchedEffect(key1 = state.value.isValid) {
        if (state.value.isValid)
            onSignIn()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ErrorText(error = state.value.error?.let { stringResource(it) })
        BasicTextField(
            value = state.value.email.field,
            errorResId = state.value.email.errorResId,
            onValueChange = { viewModel.onEvent(SignInEvent.TypeEmail(it)) })
        PasswordField(
            value = state.value.password.field,
            onValueChange = { viewModel.onEvent(SignInEvent.TypePassword(it)) },
            label = stringResource(R.string.password),
            isVisible = state.value.isPasswordVisible,
            errorResId = state.value.password.errorResId
        ) { viewModel.onEvent(SignInEvent.ChangeVisibility) }
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = {
                viewModel.validate()
            },
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen(viewModel()) { }
}