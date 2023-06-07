package com.example.matchtrackerpro.adapter

import android.content.Context
import android.os.Bundle
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
import com.example.matchtrackerpro.SharedViewModel
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.databinding.LeagueItemBinding
import com.example.matchtrackerpro.ui.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

class LeagueAdapter (
    private val dataset: List<League>,
    ): RecyclerView.Adapter<LeagueAdapter.ItemViewHolder>() {

    class ItemViewHolder (val view: View): RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.iv_league)
        val cvLeague: MaterialCardView = view.findViewById(R.id.cv_league)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val leagueAdapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.league_item, parent, false)

        return ItemViewHolder(leagueAdapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val league = dataset[position]

        val imgUri = league.leagueImg.toUri().buildUpon().scheme("https").build()

        holder.imgView.load(imgUri) {
            transformations(RoundedCornersTransformation(10f))
        }

        holder.cvLeague.setOnClickListener {
            val id = Bundle().apply { putInt("id", league.leagueId) }

            val navController = holder.view.findNavController()
            navController.navigate(R.id.action_homeFragment_to_teamOrTableFragment, id)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}