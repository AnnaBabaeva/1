package com.bizubizu.foodjournal.ui.note

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.adapters.NoteDiaryAdapter
import com.bizubizu.foodjournal.adapters.NutritionalValueAdapter
import com.bizubizu.foodjournal.data.model.Note
import com.bizubizu.foodjournal.data.model.NoteDiary
import com.bizubizu.foodjournal.data.model.NutritionalValue
import com.bizubizu.foodjournal.databinding.FragmentNewNoteBinding
import com.bizubizu.foodjournal.ui.dialog.DontAskAgainFragment
import com.bizubizu.foodjournal.ui.dialog.RationaleFragment
import java.io.File
import java.util.*


class NewNoteFragment : Fragment(R.layout.fragment_new_note)  {

    private var _binding: FragmentNewNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewNoteViewModel
    val dateOfCreate = Date()

    // pick from gallery
    private var imageUri: Uri? = null

    private lateinit var photoFile: File
    private lateinit var photoUri: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewNoteBinding.inflate(layoutInflater, container, false)


        viewModel = ViewModelProvider(this).get(NewNoteViewModel::class.java)
        viewModel.note = Note()


        ////////////////////////////////////////////////////////////////////////////////////
       //сохранение файла
        photoFile = viewModel.getPhotoFile(note)
        //добавление  свойства URI фотографии
       photoUri = FileProvider.getUriForFile(requireActivity(),
        "com.bizubizu.foodjournal.fileProvider",
        photoFile
        )


        //Date
        binding.newNoteDate.text = DateFormat.format("MMMM d, yyyy ", dateOfCreate.time)
        binding.newNoteTime.text = DateFormat.format("hh:mm", dateOfCreate.time)

        //recyclerViewNoteDiary
        if (viewModel.note!!.diaryList == null)
            viewModel.note!!.diaryList = mutableListOf(NoteDiary("", "")) // размер списка не ноль

        val noteDiaryAdapter = NoteDiaryAdapter(viewModel.note!!.diaryList!!)
        binding.recyclerViewNoteDiary.adapter = noteDiaryAdapter

        binding.newNoteAddButtonDiary.setOnClickListener {
            viewModel.note!!.diaryList!!.add(NoteDiary("", ""))
            noteDiaryAdapter.notifyItemInserted(viewModel.note!!.diaryList!!.size - 1)
        }

        //recycler nutritionalValue
        if (viewModel.note!!.nutritionalValueList == null)
            viewModel.note!!.nutritionalValueList = mutableListOf(NutritionalValue("", 0)) // размер списка не ноль

        val nutritionalValueAdapter = NutritionalValueAdapter(viewModel.note!!.nutritionalValueList!!)
        binding.recyclerViewCalories.adapter = nutritionalValueAdapter

        binding.newNoteAddButtonCalories.setOnClickListener {
            viewModel.note!!.nutritionalValueList!!.add(NutritionalValue("", 0))
            nutritionalValueAdapter.notifyItemInserted(viewModel.note!!.nutritionalValueList!!.size - 1)
        }

        addSpinners()

        //cameraButton
        binding.newNoteButtonAddImage.setOnClickListener {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                // we need to tell user why do we need permission
                showRationaleDialog()
            } else {
                cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }

        //camera
        setFragmentResultListener(RATIONALE_KEY) { _, bundle ->
            val isWantToAllowAfterRationale = bundle.getBoolean(RESULT_KEY)
            if (isWantToAllowAfterRationale) {
                cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
        //camera
        setFragmentResultListener(SETTINGS_KEY) { _, bundle ->
            val isWantToOpenSettings = bundle.getBoolean(RESULT_KEY)
            if (isWantToOpenSettings) {
                openSettings()
            }
        }

        return binding.root
    }

    fun addSpinners() {
        val dropdownCauseFoodStrings = resources.getStringArray(R.array.dropdown_cause_food)
        val arrayAdapterDropdownCauseFood =
            ArrayAdapter(requireContext(), R.layout.option_item_new_note, dropdownCauseFoodStrings)
        binding.dropdownCauseFood.setAdapter(arrayAdapterDropdownCauseFood)
        if(viewModel.note!!.causeFood == "")
            viewModel.note!!.causeFood = dropdownCauseFoodStrings[0]
        binding.dropdownCauseFood.setText(viewModel.note!!.causeFood, false)

        val dropdownHunger = resources.getStringArray(R.array.dropdown_hunger)
        val arrayAdapterDropdownHunger =
            ArrayAdapter(requireContext(), R.layout.option_item_new_note, dropdownHunger)
        binding.dropdownHunger.setAdapter(arrayAdapterDropdownHunger)
        if(viewModel.note!!.hunger == "")
            viewModel.note!!.hunger = dropdownHunger[0]
        binding.dropdownHunger.setText(viewModel.note!!.hunger, false)

        val dropdownSensationsBeforeEating =
            resources.getStringArray(R.array.dropdown_sensations_before_eating)
        val arrayAdapterDropdownSensationsBeforeEating = ArrayAdapter(
            requireContext(), R.layout.option_item_new_note, dropdownSensationsBeforeEating
        )
        binding.dropdownSensationsBeforeEating.setAdapter(arrayAdapterDropdownSensationsBeforeEating)
        if(viewModel.note!!.sensationsBeforeEating == "")
            viewModel.note!!.sensationsBeforeEating = dropdownSensationsBeforeEating[0]
        binding.dropdownSensationsBeforeEating.setText(
            viewModel.note!!.sensationsBeforeEating,
            false
        )

        val dropdownSensationsFeelingsBefore =
            resources.getStringArray(R.array.dropdown_sensations_feelings_before)
        val arrayAdapterDropdownSensationsFeelingsBefore = ArrayAdapter(
            requireContext(), R.layout.option_item_new_note, dropdownSensationsFeelingsBefore
        )
        binding.dropdownSensationsFeelingsBefore.setAdapter(
            arrayAdapterDropdownSensationsFeelingsBefore
        )
        if(viewModel.note!!.sensationsFeelingsBefore == "")
            viewModel.note!!.sensationsFeelingsBefore = dropdownSensationsFeelingsBefore[0]
        binding.dropdownSensationsFeelingsBefore.setText(
            viewModel.note!!.sensationsFeelingsBefore,
            false
        )


        val dropdownTasteOfFood = resources.getStringArray(R.array.dropdown_taste_of_food)
        val arrayAdapterDropdownTasteOfFood = ArrayAdapter(
            requireContext(),
            R.layout.option_item_new_note,
            dropdownTasteOfFood
        )
        binding.dropdownTasteFood.setAdapter(arrayAdapterDropdownTasteOfFood)
        if(viewModel.note!!.tasteFood == "")
            viewModel.note!!.tasteFood = dropdownTasteOfFood[0]
        binding.dropdownTasteFood.setText(viewModel.note!!.tasteFood, false)

        val dropdownSensationsAfterEating =
            resources.getStringArray(R.array.dropdown_sensations_after_eating)
        val arrayAdapterDropdownSensationsAfterEating = ArrayAdapter(
            requireContext(),
            R.layout.option_item_new_note,
            dropdownSensationsAfterEating
        )
        binding.dropdownSensationsAfterEating.setAdapter(arrayAdapterDropdownSensationsAfterEating)
        if(viewModel.note!!.sensationsAfterEating == "")
            viewModel.note!!.sensationsAfterEating = dropdownSensationsAfterEating[0]
        binding.dropdownSensationsAfterEating.setText(viewModel.note!!.sensationsAfterEating, false)

        val dropdownSensationsFeelingsAfter = resources.getStringArray(R.array.dropdown_sensations_feelings_after)
        val arrayAdapterDropdownSensationsFeelingsAfter = ArrayAdapter(
            requireContext(),
            R.layout.option_item_new_note,
            dropdownSensationsFeelingsAfter
        )
        binding.dropdownSensationsFeelingsAfter.setAdapter(
            arrayAdapterDropdownSensationsFeelingsAfter
        )
        if(viewModel.note!!.sensationsFeelingsAfter == "")
            viewModel.note!!.sensationsFeelingsAfter = dropdownSensationsFeelingsAfter[0]
        binding.dropdownSensationsFeelingsAfter.setText(
            viewModel.note!!.sensationsFeelingsAfter,
            false
        )
    }

    private val cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> {
                // user granted permission
                cameraShot.launch(cameraUri)
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // user denied permission and set Don't ask again.
                showSettingsDialog()
            }
            else -> {
                Toast.makeText(context, R.string.denied_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //camera
    private val cameraShot = registerForActivityResult(ActivityResultContracts.TakePicture()) {
        binding.newNoteImage.setImageURI(cameraUri)
    }

    private fun createUri(): Uri {
        val file = File(requireContext().filesDir, "picFromCamera")
        return FileProvider.getUriForFile(
            requireContext(),
           requireContext().packageName + ".provider",
            file
        )
    }

    private val cameraUri: Uri get() = createUri()

   /* //camera
    private val cameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        when {
            granted -> {
                // user granted permission
                cameraShot.launch()
            }
            !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                // user denied permission and set Don't ask again.
                showSettingsDialog()
            }
            else -> {
                showToast(R.string.denied_toast)
            }
        }
    }

    //camera
    private val cameraShot = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            binding.newNoteImage.setImageBitmap(bitmap)
        } else {
            // something was wrong
            showToast(R.string.something_wrong)
        }
    }*/

    //camera
    companion object {
        const val RATIONALE_KEY = "rationale_tag"
        const val SETTINGS_KEY = "settings_tag"
        const val RESULT_KEY = "camera_result_key"
    }

    //camera
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(Uri.fromParts("package", requireContext().packageName, null))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    //camera Dialog
    private fun showRationaleDialog() {
        RationaleFragment().show(parentFragmentManager, RationaleFragment.TAG)
    }

    //camera Dialog
    private fun showSettingsDialog() {
        DontAskAgainFragment().show(parentFragmentManager, DontAskAgainFragment.TAG)
    }

    override fun onStop() {
        super.onStop()
        viewModel.insertNote()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




    //////////////////////////////////////////////////////////////// вставляет картинку из галереи
    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryFragmentResultLauncher.launch(intent)
    }
    private val galleryFragmentResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        ActivityResultCallback<ActivityResult>{result ->
            if (result.resultCode == Activity.RESULT_OK){
                //image picked
                Toast.makeText(context, R.string.denied_toast, Toast.LENGTH_SHORT).show()
                    //get image uri
                val intent = result.data
                imageUri = intent!!.data
                //set image to imageView
                binding.newNoteImage.setImageURI(imageUri)
            }
            else{
                //canceled
                Toast.makeText(context, R.string.denied_toast, Toast.LENGTH_SHORT).show()
            }
        }
    )




}