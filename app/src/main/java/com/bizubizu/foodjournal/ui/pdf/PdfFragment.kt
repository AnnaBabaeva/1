package com.bizubizu.foodjournal.ui.pdf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.databinding.FragmentAppetiteControlBinding
import com.bizubizu.foodjournal.databinding.FragmentPdfBinding


class PdfFragment : Fragment() {

    private var _binding: FragmentPdfBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPdfBinding.inflate(layoutInflater, container, false)


        binding.button2.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.nav_profile)
            /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  .setAction("Action", null).show()*/
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}