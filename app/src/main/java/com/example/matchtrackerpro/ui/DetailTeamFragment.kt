package com.example.matchtrackerpro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.SharedViewModel
import com.example.matchtrackerpro.databinding.FragmentDetailTeamBinding

class DetailTeamFragment : Fragment() {

    //Instanz des SharedViewModel wird erstellt
    private val viewModel: SharedViewModel by activityViewModels()

    //Binding wird deklariert
    private lateinit var binding: FragmentDetailTeamBinding

    //Variable teamId wird mit dem Wert 0 initialisiert
    private var teamId = 0

    //Das Layout wird erstellt und Binding wird dem Layout zugewiesen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_team, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //der Wert des teamId Arguments wird aufgerufen und der variable teamId zugewiesen
        arguments?.let {
            teamId = it.getInt("teamId")
        }

        //Methode getTeam wird über das ViewModel aufgerufen und der Variable team zugewiesen
        val team = viewModel.getTeam(teamId)

        //Die Daten werden, mit Hilfe von Binding, in die entsprechenden Ansichten gesetzt
        binding.ivTeamLogo.load(team.img)
        binding.tvTeamName.text = team.teamName
        binding.tvFounding.text = team.founding
        binding.tvColors.text = team.colors
        binding.tvStadium.text = team.stadium
        binding.tvSeats.text = team.seats

        //SetOnClickListener wird auf den fabBack-Button gesetzt, dieser führt die Navigation zum vorherigen Bildschirm durch
        binding.fabBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}