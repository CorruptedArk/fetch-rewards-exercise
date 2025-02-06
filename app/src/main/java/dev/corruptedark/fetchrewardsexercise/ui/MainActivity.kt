package dev.corruptedark.fetchrewardsexercise.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.corruptedark.fetchrewardsexercise.data.FetchRewardsItem
import dev.corruptedark.fetchrewardsexercise.ui.theme.FetchRewardsExerciseTheme
import dev.corruptedark.fetchrewardsexercise.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchRewardsExerciseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FetchRewardsItemListView(Modifier.fillMaxSize().consumeWindowInsets(innerPadding), emptyList())
                }
            }
        }
    }
}


@Composable
fun FetchRewardsItemListView(modifier: Modifier, rewardsItems: List<FetchRewardsItem>) {
    LazyColumn (modifier = modifier) {
        items(rewardsItems) { rewardsItem ->
            FetchRewardsItemView(modifier, rewardsItem)
        }
    }

}

@Composable
fun FetchRewardsItemView(modifier: Modifier, rewardsItem: FetchRewardsItem) {
    Column(modifier = modifier) {
        Text("id: ${rewardsItem.id}")
        Text("listId: ${rewardsItem.listId}")
        Text("name: ${rewardsItem.name}")
    }

}



