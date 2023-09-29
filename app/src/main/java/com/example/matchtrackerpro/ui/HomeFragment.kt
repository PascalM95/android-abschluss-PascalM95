package com.example.matchtrackerpro.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.SharedViewModel
import com.example.matchtrackerpro.adapter.LeagueAdapter
import com.example.matchtrackerpro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    //Instanz des SharedViewModel wird erstellt
    private val viewModel: SharedViewModel by activityViewModels()

    //Binding wird deklariert
    private lateinit var binding: FragmentHomeBinding

    //Das Layout wird erstellt und Binding wird dem Layout zugewiesen
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Die Funktion loadLeagues aus dem ViewModel wird aufgerufen
        viewModel.loadLeagues()

        //Die LiveData leagues aus dem ViewModel wird beobachtet und wenn sich der Wert ändert, wird der LeagueAdapter neu erstellt
        //und mit der Liste an Ligen initialisiert
        //Anschließend wird der Adapter mit Hilfe von Binding der RecyclerView zugewiesen
        viewModel.leagues.observe(viewLifecycleOwner) {
            val adapter = LeagueAdapter(it)
            binding.rvLeague.adapter = adapter
        }
    }
}