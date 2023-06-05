package com.example.matchtrackerpro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.SharedViewModel
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.databinding.LeagueItemBinding
import com.example.matchtrackerpro.databinding.TableItemBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TableAdapter (
    private val dataset: List<Team>,
    private val viewModel: SharedViewModel
    ): RecyclerView.Adapter<TableAdapter.ItemViewHolder>() {

        class ItemViewHolder (val view: View): RecyclerView.ViewHolder(view) {
            val place = view.findViewById<TextView>(R.id.tv_place)
            val team = view.findViewById<TextView>(R.id.tv_team)
            val games = view.findViewById<TextView>(R.id.tv_matches)
            val goals = view.findViewById<TextView>(R.id.tv_goals)
            val points = view.findViewById<TextView>(R.id.tv_points)
            val back = view.findViewById<FloatingActionButton>(R.id.fab_back)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

            val tableAdapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.table_item, parent, false)

            return ItemViewHolder(tableAdapterLayout)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val team = dataset[position]

            holder.place.text = team.teamId.toString()
            holder.team.text = team.teamName
            holder.games.text = team.games.toString()
            holder.goals.text = team.goals
            holder.points.text = team.points.toString()

            holder.back.setOnClickListener {
                val navController = holder.view.findNavController()
                navController.navigate(R.id.action_tableFragment_to_homeFragment)
            }

            holder.team.setOnClickListener {
                val navController = holder.view.findNavController()
                navController.navigate(R.id.action_tableFragment_to_teamFragment)
            }
        }

        override fun getItemCount(): Int {
            return dataset.size
        }
}