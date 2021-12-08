package com.bizubizu.foodjournal.ui.profile

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.databinding.FragmentProfileBinding
import com.bizubizu.foodjournal.requestPermission
import kotlin.properties.ReadOnlyProperty



class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val IMAGE_PICK_STORAGE_CODE = 103

    private val readStoragePermissionResult: ActivityResultLauncher<String> by requestPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        granted = {
            //galleryShot.launch()
            pickFromGallery()

        }, denied = {
            showToast(R.string.denied_toast)
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        binding.profileImageView.setOnClickListener{
            //gallery???
            readStoragePermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //gallery
    private val galleryShot = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            binding.profileImageView.setImageBitmap(bitmap)
        } else {
            // something was wrong
            showToast(R.string.something_wrong)
        }
    }

    //gallery Toast
    private fun showToast(textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show()
    }

    //Эта функция берет фото из галереи
    private fun pickFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(
            galleryIntent,
            IMAGE_PICK_STORAGE_CODE
        )
    }

}