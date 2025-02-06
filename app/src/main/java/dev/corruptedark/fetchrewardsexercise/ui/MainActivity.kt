package dev.corruptedark.fetchrewardsexercise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.corruptedark.fetchrewardsexercise.data.FetchRewardsItem
import dev.corruptedark.fetchrewardsexercise.ui.theme.FetchRewardsExerciseTheme
import dev.corruptedark.fetchrewardsexercise.viewmodel.MainViewModel
import java.lang.ref.WeakReference

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()
        viewModel.updateData(WeakReference(applicationContext))
        enableEdgeToEdge()
        setContent {
            FetchRewardsExerciseTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {Text("Fetch Rewards Exercise")})
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FetchRewardsItemListView(Modifier.fillMaxSize().consumeWindowInsets(innerPadding), viewModel)
                }
            }
        }
    }
}


@Composable
fun FetchRewardsItemListView(modifier: Modifier, viewModel: MainViewModel) {
    val rewardsItems by viewModel.fetchRewardsItemListStateFlow.collectAsStateWithLifecycle()
    LazyColumn (modifier = modifier) {
        items(rewardsItems) { rewardsItem ->
            FetchRewardsItemView(modifier, rewardsItem)
        }
    }

}

@Composable
fun FetchRewardsItemView(modifier: Modifier, rewardsItem: FetchRewardsItem) {
    Column(modifier = modifier.padding(10.dp)) {
        Text("listId: ${rewardsItem.listId}")
        Text("name: ${rewardsItem.name}")
        Text("id: ${rewardsItem.id}")
    }

}



