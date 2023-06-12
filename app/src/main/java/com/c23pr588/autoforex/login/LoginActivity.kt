package com.c23pr588.autoforex.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.c23pr588.autoforex.MainActivity
import com.c23pr588.autoforex.databinding.ActivityLoginBinding
import com.c23pr588.autoforex.viewmodel.LoginViewModel
import com.c23pr588.autoforex.viewmodel.RegistrationViewModel
import com.c23pr588.autoforex.viewmodel.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()

    private var username: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edLoginUsername.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                username = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.edLoginPassword .addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.submitLogin.setOnClickListener {
            if (username.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Silakan masukkan Username.", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Silakan masukkan Password.", Toast.LENGTH_SHORT).show()
            } else {
                loginViewModel.getLoginDetails(username, password)

                loginViewModel.isLoading.observe(this) {
                    if (it) {
                        binding?.progressLogin?.visibility = View.VISIBLE
                    } else {
                        binding?.progressLogin?.visibility = View.GONE
                    }
                }

                loginViewModel.loginDetails.observe(this) {
                    if (it.status == 200) {
                        loginViewModel.saveUsername(username)
                        loginViewModel.savePassword(password)
                        loginViewModel.saveLogin()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

                    }
                }
            }
        }
    }
}