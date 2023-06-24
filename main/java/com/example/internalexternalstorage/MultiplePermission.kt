package com.example.internalexternalstorage

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.internalexternalstorage.databinding.ActivityMultiplePermissionBinding

class MultiplePermission : AppCompatActivity() {

    private val registerActivityforResult=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        permission ->
        if (permission[android.Manifest.permission.CALL_PHONE]==true){
            Toast.makeText(this, " Call contact is granted", Toast.LENGTH_SHORT).show()
        }
        if (permission[android.Manifest.permission.RECEIVE_SMS]==true) {
            Toast.makeText(this, "SMS contact is granted", Toast.LENGTH_SHORT).show()
        }
        if (permission[android.Manifest.permission.ACCESS_BACKGROUND_LOCATION]==true) {
            Toast.makeText(this, "Location contact is granted", Toast.LENGTH_SHORT).show()
        }
    }

    lateinit var binding: ActivityMultiplePermissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMultiplePermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMulty.setOnClickListener {
            takemultypalpermissions()
        }

    }

    private fun takemultypalpermissions() {
    if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE )
        !=PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED||
        ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)!=PackageManager.PERMISSION_GRANTED){
        registerActivityforResult.launch(
            arrayOf(
                android.Manifest.permission.CALL_PHONE,
                android.Manifest.permission.RECEIVE_SMS,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION))
    }else{
        registerActivityforResult.launch(
            arrayOf(
                android.Manifest.permission.CALL_PHONE,
            android.Manifest.permission.RECEIVE_SMS,
            android.Manifest.permission.ACCESS_BACKGROUND_LOCATION))
    }
    }
}