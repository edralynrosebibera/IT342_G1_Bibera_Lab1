package com.example.mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email =
            findViewById<EditText>(R.id.emailInput)

        val password =
            findViewById<EditText>(R.id.passwordInput)

        val loginBtn =
            findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val request = LoginRequest(
                email.text.toString(),
                password.text.toString()
            )

            RetrofitClient.instance.login(request)
                .enqueue(object : Callback<AuthResponse> {

                    override fun onResponse(
                        call: Call<AuthResponse>,
                        response: Response<AuthResponse>
                    ) {

                        if (response.isSuccessful
                            && response.body()?.success == true
                        ) {

                            Toast.makeText(
                                this@LoginActivity,
                                "Login Success!",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            Toast.makeText(
                                this@LoginActivity,
                                "Login Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<AuthResponse>,
                        t: Throwable
                    ) {

                        Toast.makeText(
                            this@LoginActivity,
                            "Connection Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }
}
