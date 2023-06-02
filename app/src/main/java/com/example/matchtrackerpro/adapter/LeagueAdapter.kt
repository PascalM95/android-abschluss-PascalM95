package com.example.matchtrackerpro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.databinding.LeagueItemBinding
import com.example.matchtrackerpro.ui.HomeFragmentDirections

class LeagueAdapter (
    private val dataset: List<League>
    ): RecyclerView.Adapter<LeagueAdapter.ItemViewHolder>() {

    class ItemViewHolder (val binding: LeagueItemBinding): RecyclerView.ViewHolder(binding.root) {
        val imgView = binding.ivLeague
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = LeagueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val league = dataset[position]

        val imgUri = league.leagueImg.toUri().buildUpon().scheme("https").build()

        holder.imgView.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}