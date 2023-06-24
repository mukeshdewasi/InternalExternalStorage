package com.example.internalexternalstorage

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    val callcontract = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            showCallPermissionDialog()
        }
    }
    val Smscontract=registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it){
            Toast.makeText(this,"Permission granted",Toast.LENGTH_SHORT).show()
        }else{
            showSmspermissionDialg()
        }
    }

    private fun showSmspermissionDialg() {
        AlertDialog.Builder(this).apply {
            setTitle("Permission Required")
            setMessage("This app need access to your Sms ")
            setPositiveButton("Settings", DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            })
            setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

            })
        }.show()

    }

    private fun showCallPermissionDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Permission Required")
            setMessage("This app need access to your call ")
            setPositiveButton("Settings", DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            })
            setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

            })
        }.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_call_phone).setOnClickListener {
            callcontract.launch(android.Manifest.permission.CALL_PHONE)
        }
        findViewById<Button>(R.id.btn_Receive_sms).setOnClickListener {
            Smscontract.launch(android.Manifest.permission.RECEIVE_SMS)
        }
    }
}