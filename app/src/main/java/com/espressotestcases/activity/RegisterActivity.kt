package com.espressotestcases.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import com.espressotestcases.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registerButton -> {
                register()
            }
        }
    }

    private fun register() {

        //resetErrors
        firstNameEditText.error = null
        lastNameEditText.error = null
        emailEditText.error = null
        passwordNameEditText.error = null
        confirmPasswordNameEditText.error = null

        var check = false
        var focusOnView: View? = null


        if (TextUtils.isEmpty(firstNameEditText.text.toString())) {
            firstNameEditText.error = getString(R.string.first_name_required)
            focusOnView = firstNameEditText
            check = true

        } else if (TextUtils.isEmpty(lastNameEditText.text.toString())) {
            lastNameEditText.error = getString(R.string.last_name_required)
            focusOnView = lastNameEditText
            check = true
        } else if (TextUtils.isEmpty(emailEditText.text.toString())) {
            emailEditText.error = getString(R.string.email_required)
            focusOnView = emailEditText
            check = true
        } else if (!isEmailValid(emailEditText.text.toString())) {
            emailEditText.error = getString(R.string.invalid_email)
            focusOnView = emailEditText
            check = true
        } else if (TextUtils.isEmpty(passwordNameEditText.text.toString())) {
            passwordNameEditText.error = getString(R.string.password_required)
            focusOnView = passwordNameEditText
            check = true
        } else if (!isPasswordValid(passwordNameEditText.text.toString())) {
            passwordNameEditText.error = getString(R.string.invalid_password)
            focusOnView = passwordNameEditText
            check = true
        } else if (TextUtils.isEmpty(confirmPasswordNameEditText.text.toString())) {
            confirmPasswordNameEditText.error = getString(R.string.confirm_password_required)
            focusOnView = confirmPasswordNameEditText
            check = true
        } else if (!isPasswordValid(confirmPasswordNameEditText.text.toString())) {
            confirmPasswordNameEditText.error = getString(R.string.invalid_password)
            focusOnView = confirmPasswordNameEditText
            check = true
        } else if (!matchPassword(
                passwordNameEditText.text.toString(),
                confirmPasswordNameEditText.text.toString()
            )
        ) {
            confirmPasswordNameEditText.error = getString(R.string.password_not_match)
            focusOnView = confirmPasswordNameEditText
            check = true
        }
        if (check)
            focusOnView!!.requestFocus()
        else {
            registerSuccess()
        }

    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun matchPassword(password: String, confirmPassword: String): Boolean {
        return password.equals(confirmPassword)
    }

    private fun registerSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("welcomeMessage", "Hi registration successfully done !")
        startActivity(intent)
        finish()
    }

}

