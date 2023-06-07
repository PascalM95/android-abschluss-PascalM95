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
import com.example.matchtrackerpro.databinding.FragmentDetailTeamBinding
import com.example.matchtrackerpro.databinding.FragmentTeamBinding

class DetailTeamFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var binding: FragmentDetailTeamBinding

    private var teamId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            teamId = it.getInt("teamId")
        }
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

        //todo
        binding.ivTeamLogo.setImageResource(0)

        binding.tvTeamName.setText(0)

        binding.tvFounding.setText(0)

        binding.tvColors.setText(0)

        binding.tvStadium.setText(0)

        binding.tvSeats.setText(0)

        binding.fabBack.setOnClickListener {
            findNavController().navigate(DetailTeamFragmentDirections.actionDetailTeamFragmentToTeamFragment())
        }
    }
}