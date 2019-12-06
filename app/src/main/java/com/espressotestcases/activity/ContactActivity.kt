package com.espressotestcases.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.espressotestcases.R

class ContactActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setResult(Activity.RESULT_OK,
            createResultData("896-745-2310")
        )
        finish()
    }


    companion object {
        fun createResultData(phoneNumber: String): Intent {
            var resultData = Intent()
            resultData.putExtra("key_phone_number", phoneNumber)
            return resultData
        }
    }
}
