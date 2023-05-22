package com.example.matchtrackerpro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.databinding.LeagueItemBinding
import com.example.matchtrackerpro.ui.HomeFragmentDirections

class LeagueAdapter (
    private val context: Context,
    private val dataset: List<Any>
    ): RecyclerView.Adapter<LeagueAdapter.ItemViewHolder>() {

    inner class ItemViewHolder (val binding: LeagueItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = LeagueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val league = dataset[position]

        TODO()

        holder.binding.cvLeague.setOnClickListener {
            val navController = holder.binding.cvLeague.findNavController()
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToTableFragment())
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}