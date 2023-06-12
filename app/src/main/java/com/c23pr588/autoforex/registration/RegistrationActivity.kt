package com.c23pr588.autoforex.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.c23pr588.autoforex.databinding.ActivityRegistrationBinding
import com.c23pr588.autoforex.login.LoginActivity
import com.c23pr588.autoforex.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    val registrationViewModel: RegistrationViewModel by viewModels()

    private var firstName: String = ""
    private var lastName: String = ""
    private var email: String = ""
    private var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edRegisterFirstName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                firstName = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.edRegisterLastName.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lastName = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.edRegisterEmail.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                email = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.edRegisterPassword.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                password = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.submitRegister.setOnClickListener {
            if (email.isEmpty()) {
                Toast.makeText(this@RegistrationActivity, "Tolong lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else if (firstName.isEmpty()) {
                Toast.makeText(this@RegistrationActivity, "Tolong lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else if (lastName.isEmpty()) {
                Toast.makeText(this@RegistrationActivity, "Tolong lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()){
                Toast.makeText(this@RegistrationActivity, "Tolong lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                registrationViewModel.getRegistrationDetails(firstName, lastName, email, password)
                registrationViewModel.isLoading.observe(this) {
                    if (it) {
                        binding.progressRegister.visibility = View.VISIBLE
                    } else {
                        binding.progressRegister.visibility = View.GONE
                        startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
                    }
                }
            }
        }
    }
}