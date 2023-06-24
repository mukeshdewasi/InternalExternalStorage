package com.example.internalexternalstorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.internalexternalstorage.databinding.ActivityImagePickerBinding

class ImagePickerActivity : AppCompatActivity() {

    lateinit var binding: ActivityImagePickerBinding

    val stroageContract=registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it){
            pickimageformgallery()
        }else{
            Toast.makeText(this, "Allow Permission", Toast.LENGTH_SHORT).show()
        }
    }
    private fun pickimageformgallery() {
        var intent=Intent()
        intent.type="image/*"
        //intent.action=Intent.ACTION_PICK
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)
    }

    var galleryContract=registerForActivityResult(ActivityResultContracts.GetContent()){
        if (it!=null){
            binding.ivImage.setImageURI(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivImage.setOnClickListener {
        //    stroageContract.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                galleryContract.launch("image/*")
                intent.putExtra("IMAGE","list")
        }
    }
         override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode==100 && resultCode == RESULT_OK){
                data?.let {
                    binding.ivImage.setImageURI(it.data)
                }
            }
        }

    }
