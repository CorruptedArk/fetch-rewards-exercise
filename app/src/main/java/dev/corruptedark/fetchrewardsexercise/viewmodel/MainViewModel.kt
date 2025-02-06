package dev.corruptedark.fetchrewardsexercise.viewmodel

import androidx.lifecycle.ViewModel
import dev.corruptedark.fetchrewardsexercise.repositories.JSONDownloadRepository

class MainViewModel : ViewModel() {
    val jsonDownloadRepository = JSONDownloadRepository()
    
}