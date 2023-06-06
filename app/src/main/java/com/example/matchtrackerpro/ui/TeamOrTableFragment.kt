package com.example.matchtrackerpro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.databinding.FragmentTeamOrTableBinding


class TeamOrTableFragment : Fragment() {

    private lateinit var binding: FragmentTeamOrTableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_team_or_table, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvTeams.setOnClickListener {
            findNavController().navigate(R.id.action_teamOrTableFragment_to_teamFragment)
        }

        binding.cvTable.setOnClickListener {
            findNavController().navigate(R.id.action_teamOrTableFragment_to_tableFragment)
        }

        binding.fabBack.setOnClickListener {
            findNavController().navigate(R.id.action_teamOrTableFragment_to_homeFragment)
        }
    }
}