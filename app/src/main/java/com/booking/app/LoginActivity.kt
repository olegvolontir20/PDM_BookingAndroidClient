package com.booking.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booking.app.api.RetrofitClient
import com.booking.app.api.models.LoginModel
import com.booking.app.api.models.RegisterModel
import com.booking.app.ui.theme.BookingAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent() {
            BookingAppTheme {
                LoginSignupScreen()
            }
        }

//        val call: Call<PaginatedResponse<Apartment>> = RetrofitClient.apartmentEndpoint.getApartments()
//        val context = this
//
//        call.enqueue(object : Callback<PaginatedResponse<Apartment>> {
//            override fun onResponse(
//                call: Call<PaginatedResponse<Apartment>>,
//                response: Response<PaginatedResponse<Apartment>>
//            ) {
//                if (response.isSuccessful) {
//                    val data: PaginatedResponse<Apartment>? = response.body()
//                    val mess = data?.items?.get(0)?.description.toString()
//                    showToast("Apartment1 descr: " + mess, context)
//                } else {
//                    showToast("onResponse failed", context)
//                }
//            }
//
//            override fun onFailure(call: Call<PaginatedResponse<Apartment>>, t: Throwable) {
//                showToast(t.message.toString(), context)
//            }
//        })
    }
}

fun userLogin(loginModel: LoginModel, context: Context, onFail: () -> Unit){
    val call: Call<Void> = RetrofitClient.userEndpoint.loginUser(loginModel)

    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                showToast(response.code().toString(), context)
                context.startActivity(Intent(context, MainActivity::class.java))
                (context as Activity).finish()
            } else {
                showToast(response.message(), context)
                onFail.invoke()
            }
        }
        override fun onFailure(call: Call<Void>, t: Throwable) {
            showToast(t.message.toString(), context)
            onFail.invoke()
        }
    })
}

fun userSignup(
    registerModel: RegisterModel,
    context: Context,
    onFail: () -> Unit,
    onSuccess: () -> Unit,
    confirmPassword: String
){
    if(registerModel.password != confirmPassword)
    {
        onFail.invoke()
        return
    }

    val call: Call<Void> = RetrofitClient.userEndpoint.registerUser(registerModel)

    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                showToast(response.code().toString(), context)
                onSuccess.invoke()
            } else {
                showToast(response.message(), context)
                onFail.invoke()
            }
        }
        override fun onFailure(call: Call<Void>, t: Throwable) {
            showToast(t.message.toString(), context)
            onFail.invoke()
        }
    })
}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Preview
@Composable
fun PreviewLoginSignupScreen(){
    Surface {
        var isLoginScreen = false

        if(isLoginScreen)
        {
            PreviewLoginForm()
        }
        else
        {
            PreviewSignupForm()
        }
    }
}

@Composable
fun LoginSignupScreen(){
    Surface {
        var isLoginScreen: Boolean by remember {
            mutableStateOf(true)
        }

        val changeScreen: () -> Unit = {
            isLoginScreen = !isLoginScreen
        }
        
        if(isLoginScreen)
        {
            LoginForm(changeScreen)
        }
        else
        {
            SignupForm(changeScreen)
        }
    }
}

@Composable
fun PreviewSignupForm(){
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            UsernameField(
                value = "registerModel.userName",
                onChange = { },
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = "registerModel.password",
                onChange = {},
                submit = { },
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = "confirmPassword",
                label = "Confirm Password",
                placeholder = "Confirm password",
                onChange = { },
                submit = { },
                modifier = Modifier.fillMaxWidth()
            )
            EmailField(
                value = "registerModel.email",
                onChange = {},
                modifier = Modifier.fillMaxWidth()
            )
            NumberField(
                value = "registerModel.phoneNumber",
                onChange = {},
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {  },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            Button(
                onClick = {  },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Login")
            }
        }
    }
}

@Composable
fun SignupForm(
    changeScreen: () -> Unit
){
    Surface {
        var registerModel: RegisterModel by remember {
            mutableStateOf(RegisterModel())
        }

        var confirmPassword: String by remember {
            mutableStateOf("")
        }

        val context = LocalContext.current

        val resetRegisterModel: () -> Unit = {registerModel = RegisterModel(); confirmPassword = "" }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            UsernameField(
                value = registerModel.userName,
                onChange = { data -> registerModel = registerModel.copy(userName = data)},
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = registerModel.password,
                onChange = { data -> registerModel = registerModel.copy(password = data)},
                submit = { },
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = confirmPassword,
                label = "Confirm Password",
                placeholder = "Confirm password",
                onChange = { data -> confirmPassword = data },
                submit = { },
                modifier = Modifier.fillMaxWidth()
            )
            EmailField(
                value = registerModel.email,
                onChange = {data -> registerModel = registerModel.copy(email = data)},
                modifier = Modifier.fillMaxWidth()
            )
            NumberField(
                value = registerModel.phoneNumber,
                onChange = {data -> registerModel = registerModel.copy(phoneNumber = data)},
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { userSignup(registerModel, context, resetRegisterModel, changeScreen, confirmPassword) },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
            Button(
                onClick = { changeScreen.invoke() },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Login")
            }
        }
    }
}

@Composable
fun PreviewLoginForm() {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            UsernameField(
                value = "login",
                onChange = {},
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = "password",
                onChange = { },
                submit = { },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}

@Composable
fun LoginForm(
    changeScreen: () -> Unit
) {
    Surface {
        var loginModel: LoginModel by remember {
            mutableStateOf(LoginModel())
        }
        val context = LocalContext.current

        val resetLoginModel: () -> Unit = {loginModel = LoginModel() }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            UsernameField(
                value = loginModel.userName,
                onChange = { data -> loginModel = loginModel.copy(userName = data)},
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = loginModel.password,
                onChange = { data -> loginModel = loginModel.copy(password = data)},
                submit = { userLogin(loginModel, context, resetLoginModel)},
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = { userLogin(loginModel, context, resetLoginModel)},
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            Button(
                onClick = { changeScreen.invoke()},
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go to Sign Up")
            }
        }
    }
}

@Composable
fun UsernameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Login",
    placeholder: String = "Enter your Login"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    submit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password"
) {
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Star,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun EmailField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Email",
    placeholder: String = "Enter your email"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Email,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun NumberField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Phone Number",
    placeholder: String = "Enter your phone number"
){
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Phone,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

