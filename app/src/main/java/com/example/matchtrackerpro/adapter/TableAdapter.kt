package com.example.matchtrackerpro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchtrackerpro.R
import com.example.matchtrackerpro.data.datamodels.TeamData

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

            holder.place.text = (position + 1).toString()
            holder.team.text = team.teamName
            holder.games.text = team.games.toString()
            holder.goals.text = team.goals
            holder.points.text = team.points.toString()
        }

        override fun getItemCount(): Int {
            return dataset.size
        }
}