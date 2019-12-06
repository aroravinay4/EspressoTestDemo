package com.espressotestcases.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.espressotestcases.R
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val REQUEST_CODE_PICK: Int = 16
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        //getIntent data
        val userData = intent.getStringExtra("userName")
        userName.text = "Welcome " + userData


        button_pick_contact.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_pick_contact -> {
                val pickContactIntent = Intent(this, ContactActivity::class.java)
                startActivityForResult(pickContactIntent,
                    REQUEST_CODE_PICK
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == Activity.RESULT_OK) {
                edit_text_caller_number.setText(data!!.getStringExtra("key_phone_number"))
            }
        }
    }
}
