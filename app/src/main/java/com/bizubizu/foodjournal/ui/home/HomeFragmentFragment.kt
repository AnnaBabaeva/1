package com.bizubizu.foodjournal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bizubizu.foodjournal.R
import com.bizubizu.foodjournal.databinding.FragmentHomeBinding


class HomeFragmentFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //Ресайкл вью Home
        binding.recyclerViewHome.setHasFixedSize(true)
        binding.recyclerViewHome.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

       /* binding.recyclerViewHome.layoutManager = GridLayoutManager(requireContext(), 2) //1
        binding.recyclerViewHome.adapter = NotesAdapter(requireContext(), notesList) //2

        binding.recyclerViewHome.adapter = adapter
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(this)*/

        //fab
        binding.fab.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.nav_newNote)
        }

        return binding.root
    }

}