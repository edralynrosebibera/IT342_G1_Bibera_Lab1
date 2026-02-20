package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val welcomeText =
            findViewById<TextView>(R.id.welcomeText)

        val profileName =
            findViewById<TextView>(R.id.profileName)

        val profileDropdown =
            findViewById<LinearLayout>(R.id.profileDropdown)

        val session = SessionManager(this)
        val name = session.getName() ?: "User"

        welcomeText.text = "Welcome back, $name 👋"
        profileName.text = name

        profileDropdown.setOnClickListener {

            val popup = PopupMenu(this, profileDropdown)
            popup.menuInflater.inflate(R.menu.profile_menu, popup.menu)

            popup.setOnMenuItemClickListener {

                when (it.itemId) {

                    R.id.editProfile -> {

                        startActivity(
                            Intent(this, EditProfileActivity::class.java)
                        )

                        true
                    }

                    R.id.logout -> {

                        SessionManager(this).logout()

                        startActivity(
                            Intent(this, LoginActivity::class.java)
                        )

                        finish()
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }

    }
}