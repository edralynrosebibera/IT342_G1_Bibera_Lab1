package com.example.mobile

import android.content.Intent
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

        val emailInput =
            findViewById<EditText>(R.id.emailInput)

        val passwordInput =
            findViewById<EditText>(R.id.passwordInput)

        val loginBtn =
            findViewById<Button>(R.id.loginBtn)

        loginBtn.setOnClickListener {

            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val request = LoginRequest(email, password)

            RetrofitClient.instance.login(request)
                .enqueue(object : Callback<AuthResponse> {

                    override fun onResponse(
                        call: Call<AuthResponse>,
                        response: Response<AuthResponse>
                    ) {

                        if (response.isSuccessful &&
                            response.body()?.success == true
                        ) {

                            val data = response.body()!!

                            val fullName =
                                "${data.firstName} ${data.lastName}"

                            SessionManager(this@LoginActivity)
                                .saveLogin(
                                    data.token!!,
                                    fullName
                                )

                            Toast.makeText(
                                this@LoginActivity,
                                "Login Success!",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    DashboardActivity::class.java
                                )
                            )

                            finish()

                        } else {

                            Toast.makeText(
                                this@LoginActivity,
                                response.body()?.message
                                    ?: "Login Failed",
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
                            "Connection Error: ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}