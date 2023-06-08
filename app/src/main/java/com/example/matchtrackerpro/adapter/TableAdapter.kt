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
import com.example.matchtrackerpro.data.datamodels.TeamData
import com.example.matchtrackerpro.databinding.LeagueItemBinding
import com.example.matchtrackerpro.databinding.TableItemBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TableAdapter (
    private val dataset: List<TeamData>,
    ): RecyclerView.Adapter<TableAdapter.ItemViewHolder>() {

        class ItemViewHolder (val view: View): RecyclerView.ViewHolder(view) {
            val place: TextView = view.findViewById(R.id.tv_place)
            val team: TextView = view.findViewById(R.id.tv_team)
            val games: TextView = view.findViewById(R.id.tv_matches)
            val goals: TextView = view.findViewById(R.id.tv_goals)
            val points: TextView = view.findViewById(R.id.tv_points)
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
        }

        override fun getItemCount(): Int {
            return dataset.size
        }
}