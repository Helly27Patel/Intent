package com.example.studentdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {


    lateinit var btnSend:Button
    lateinit var edtEmail:EditText
    lateinit var edtPwd:EditText
    lateinit var btnCallUser:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSend = findViewById(R.id.btnLogin)
        edtEmail = findViewById(R.id.edtEmail)
        edtPwd = findViewById(R.id.edtPwd)
        btnCallUser = findViewById(R.id.btnCallUser)
    }

    override fun onStart() {
        super.onStart()

        btnSend.setOnClickListener {
            var intent:Intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("email",edtEmail.text.toString())
            intent.putExtra("pwd",edtPwd.text.toString())
            startActivity(intent)
        }

        btnCallUser.setOnClickListener {
            var intent:Intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9737916812")
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestForPermission()
            }else{
                startActivity(intent)
            }

        }
    }

    private fun requestForPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1)  {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


//    fun performCount(view: View) {
//        //increament textview value
//        mCount++
//        txtCount.text = mCount.toString()
//    }
}