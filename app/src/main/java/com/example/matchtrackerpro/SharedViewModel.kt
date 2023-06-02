package com.example.matchtrackerpro

import androidx.lifecycle.ViewModel
import com.example.matchtrackerpro.data.Repository

class SharedViewModel: ViewModel() {

    val repository = Repository()
}