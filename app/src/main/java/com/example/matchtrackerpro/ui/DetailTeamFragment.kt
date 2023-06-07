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
import com.example.matchtrackerpro.databinding.FragmentTeamBinding

class DetailTeamFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailTeamBinding

    private var teamId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        arguments?.let {
            teamId = it.getInt("teamId")
        }

        val team = viewModel.currentLeague.value?.teams?.find { it.teamId == teamId }

        binding.ivTeamLogo.load(team!!.img)
        binding.tvTeamName.text = team.teamName
        binding.tvFounding.text = team.founding
        binding.tvColors.text = team.colors
        binding.tvStadium.text = team.stadium
        binding.tvSeats.text = team.seats

        binding.fabBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}