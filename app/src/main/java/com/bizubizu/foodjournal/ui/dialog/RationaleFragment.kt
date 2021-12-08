package com.bizubizu.foodjournal.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.databinding.FragmentNewNoteBinding
import com.bizubizu.foodjournal.databinding.FragmentRationaleBinding
import com.bizubizu.foodjournal.ui.note.NewNoteFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class RationaleFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentRationaleBinding? = null
    private val binding get() = _binding!!

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRationaleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rationaleButton.setOnClickListener {
            setFragmentResult(
                NewNoteFragment.RATIONALE_KEY,
                bundleOf(NewNoteFragment.RESULT_KEY to true)
            )
            dismiss()
        }
    }

    companion object {
        const val TAG = "rationale_tag"
    }
}