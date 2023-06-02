package com.example.matchtrackerpro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.databinding.LeagueItemBinding
import com.example.matchtrackerpro.databinding.TableItemBinding

class TableAdapter (
    private val dataset: List<Team>
    ): RecyclerView.Adapter<TableAdapter.ItemViewHolder>() {

        class ItemViewHolder (val binding: TableItemBinding): RecyclerView.ViewHolder(binding.root) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val binding = TableItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val team = dataset[position]


        }

        override fun getItemCount(): Int {
            return dataset.size
        }
}