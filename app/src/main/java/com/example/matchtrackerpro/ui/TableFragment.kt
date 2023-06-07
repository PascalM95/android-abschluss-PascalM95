package com.example.matchtrackerpro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.SharedViewModel
import com.example.matchtrackerpro.adapter.TableAdapter
import com.example.matchtrackerpro.databinding.FragmentTableBinding

class TableFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var binding: FragmentTableBinding

    private var leagueId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            leagueId = it.getInt("leagueId")
        }

        if (leagueId != 0) {
            viewModel.getLeagueById(leagueId)
        }

        viewModel.currentLeague.observe(viewLifecycleOwner) {
            val adapter = TableAdapter(it.teams)
            binding.rvTable.adapter = adapter

           binding.fabBack.setOnClickListener {
               findNavController().navigateUp()
           }
        }
    }
}