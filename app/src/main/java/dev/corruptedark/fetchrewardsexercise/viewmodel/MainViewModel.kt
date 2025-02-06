package dev.corruptedark.fetchrewardsexercise.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.corruptedark.fetchrewardsexercise.data.FetchRewardsItem
import dev.corruptedark.fetchrewardsexercise.repositories.JSONDownloadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class MainViewModel : ViewModel() {
    private val jsonDownloadRepository = JSONDownloadRepository()
    val fetchRewardsItemListStateFlow: MutableStateFlow<List<FetchRewardsItem>> = MutableStateFlow(
        emptyList()
    )

    // Passing a context here is a bit of a sin, but this is an exercise, not a production app
    fun updateData(weakContext: WeakReference<Context>) {
            weakContext.get()?.let {
                jsonDownloadRepository.startDownload(it) { fetchRewardsItems ->
                    viewModelScope.launch {
                        fetchRewardsItemListStateFlow.emit(fetchRewardsItems)
                    }
                }
        }
    }

}