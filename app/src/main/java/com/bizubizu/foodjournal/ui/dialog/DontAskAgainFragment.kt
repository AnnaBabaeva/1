package com.bizubizu.foodjournal.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.databinding.FragmentDontAskAgainBinding
import com.bizubizu.foodjournal.databinding.FragmentRationaleBinding
import com.bizubizu.foodjournal.ui.note.NewNoteFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DontAskAgainFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDontAskAgainBinding? = null
    private val binding get() = _binding!!

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDontAskAgainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.settingsButton.setOnClickListener {
            setFragmentResult(
                NewNoteFragment.SETTINGS_KEY,
                bundleOf(NewNoteFragment.RESULT_KEY to true)
            )
            dismiss()
        }
    }

    companion object {
        const val TAG = "dont_ask_tag"
    }
}