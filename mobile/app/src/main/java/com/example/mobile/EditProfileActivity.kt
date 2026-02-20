package com.example.mobile

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var bioInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var initialsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        firstNameInput = findViewById(R.id.firstNameInput)
        lastNameInput = findViewById(R.id.lastNameInput)
        bioInput = findViewById(R.id.bioInput)
        phoneInput = findViewById(R.id.phoneInput)
        initialsText = findViewById(R.id.profileInitials)

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        val cancelBtn = findViewById<Button>(R.id.cancelBtn)

        cancelBtn.setOnClickListener { finish() }

        loadProfile()

        saveBtn.setOnClickListener {
            updateProfile()
        }
    }

    private fun loadProfile() {

        val token = SessionManager(this).getToken() ?: return

        RetrofitClient.instance.getProfile("Bearer $token")
            .enqueue(object : Callback<AuthResponse> {

                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {

                    if (response.isSuccessful && response.body()?.success == true) {

                        val user = response.body()!!

                        firstNameInput.setText(user.firstName ?: "")
                        lastNameInput.setText(user.lastName ?: "")


                        val initials =
                            "${user.firstName?.firstOrNull() ?: ""}" +
                                    "${user.lastName?.firstOrNull() ?: ""}"

                        initialsText.text = initials.uppercase()
                    }
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        "Failed to load profile",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }


    private fun updateProfile() {

        val token = SessionManager(this).getToken() ?: return

        val request = UpdateProfileRequest(
            firstNameInput.text.toString(),
            lastNameInput.text.toString(),
            bioInput.text.toString(),
            phoneInput.text.toString()
        )

        RetrofitClient.instance.updateProfile(
            "Bearer $token",
            request
        ).enqueue(object : Callback<AuthResponse> {

            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ) {

                if (response.isSuccessful) {
                    Toast.makeText(
                        this@EditProfileActivity,
                        "Profile Updated!",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Toast.makeText(
                    this@EditProfileActivity,
                    "Update failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}