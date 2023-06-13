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

    //Binding wird deklariert
    private lateinit var binding: FragmentTeamOrTableBinding

    //Variable leagueId wird mit dem Wert 0 initialisiert
    private var leagueId: Int = 0

    //Das Layout wird erstellt und Binding wird dem Layout zugewiesen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team_or_table, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //der Wert des leagueId Arguments wird aufgerufen und der variable leagueId zugewiesen
        arguments?.let {
            leagueId = it.getInt("leagueId")
        }

        binding.cvTeams.setOnClickListener {
            findNavController().navigate(TeamOrTableFragmentDirections.actionTeamOrTableFragmentToTeamFragment(leagueId))
        }

        binding.cvTable.setOnClickListener {
            findNavController().navigate(
                TeamOrTableFragmentDirections.actionTeamOrTableFragmentToTableFragment(leagueId))
        }

        //SetOnClickListener wird auf den fabBack-Button gesetzt, dieser führt die Navigation zum vorherigen Bildschirm durch
        binding.fabBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}