package com.example.homeworkregistrationform

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.children
import com.example.homeworkregistrationform.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.saveBtn.setOnClickListener{
            checkValidityOfInputs()
        }
        binding.clearBtn.setOnLongClickListener {
            clear()
        }
    }

    private fun checkValidityOfInputs() {
        when {
            binding.emailEditTxt.text.isNullOrEmpty() ||
                    binding.userNameEditTxt.text.isNullOrEmpty() ||
                    binding.firstNameEditTxt.text.isNullOrEmpty() ||
                    binding.lastNameEditTxt.text.isNullOrEmpty() ||
                    binding.ageEditTxt.text.isNullOrEmpty() -> toast(
                FILL_FIELDS
            )
            !binding.emailEditTxt.text.toString().checkEmail() -> toast(EMAIL_VALID)
            binding.userNameEditTxt.text?.length?.let { it < 10 } == true -> toast(USERNAME_LENGTH)
            !binding.ageEditTxt.text.toString().isDigitsOnly() -> toast(AGE_VALID)
            else -> toast(SUCCESS)
        }
    }

    private fun clear(): Boolean {
        for (view in binding.root.children) {
            if (view is EditText)
                view.text.clear()
        }
        return true
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    private fun String.checkEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()


    companion object {
        const val EMAIL_VALID = "Email is not validate"
        const val USERNAME_LENGTH = "Username length is less than 10"
        const val AGE_VALID = "Age is not integer"
        const val FILL_FIELDS = "You must fill every field"
        const val SUCCESS = "Success"
    }
}