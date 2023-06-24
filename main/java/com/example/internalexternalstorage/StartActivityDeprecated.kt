package com.example.internalexternalstorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.internalexternalstorage.databinding.ActivityMultiplePermissionBinding
import com.example.internalexternalstorage.databinding.ActivityStartDeprecatedBinding

class StartActivityDeprecated : AppCompatActivity() {

    lateinit var binding:ActivityStartDeprecatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStartDeprecatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            var intent=Intent(this,AnotherActivity::class.java)
            startActivityForResult(intent,100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==100){
            data?.let {

            }

        }
    }
}