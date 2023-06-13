package com.example.matchtrackerpro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.SharedViewModel
import com.example.matchtrackerpro.adapter.TeamAdapter
import com.example.matchtrackerpro.data.datamodels.TeamData
import com.example.matchtrackerpro.databinding.FragmentTeamBinding

class TeamFragment : Fragment() {

    //Instanz des SharedViewModel wird erstellt
    private val viewModel: SharedViewModel by activityViewModels()

    //Binding wird deklariert
    private lateinit var binding: FragmentTeamBinding

    //Variable leagueId wird mit dem Wert 0 initialisiert
    private var leagueId = 0

    //Eine lateinit Variable wird erstellt, sie enthält eine Liste von TeamData und wird erst später initialisiert
    private lateinit var teams: List<TeamData>

    //Das Layout wird erstellt und Binding wird dem Layout zugewiesen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //der Wert des leagueId Arguments wird aufgerufen und der variable leagueId zugewiesen
        arguments?.let {
            leagueId = it.getInt("leagueId")
        }

        //Es wird überprüft, ob die leagueId nicht 0 ist, wenn dies der Fall ist wird die getTeams-Funktion
        //aus dem ViewModel aufgerufen und die leagueId wird als Parameter übergeben
        if (leagueId != 0) {
            teams = viewModel.getTeams(leagueId)
        }

        //ein neuer Adapter wird erstellt und mit dem übergebenen Parameter teams initialisiert
        //Anschließend wird der neue Adapter der RecyclerView zugewiesen
        val adapter = TeamAdapter(teams)
        binding.rvTeams.adapter = adapter

        //SetOnClickListener wird auf den fabBack-Button gesetzt, dieser führt die Navigation zum vorherigen Bildschirm durch
        binding.fabBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}