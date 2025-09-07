package com.example.navigation3sample.screen1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import com.example.navigation3sample.Screen1Key
import com.example.navigation3sample.Screen2Key

@Composable
fun Screen1(
    viewModel: Screen1ViewModel,
    incrementCount: () -> Unit,
    navigateTo: (NavKey) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO: ライフサイクを考慮した収集、collectAsStateWithLifecycleはうまく動かない
    val count by viewModel.count.collectAsStateWithLifecycle()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(Screen1Key.TITLE)
        Text("count: $count")
        Button(
            onClick = { navigateTo(Screen2Key(count = count)) },
        ) {
            Text(text = "navigation to screen2")
        }
        Button(
            onClick = incrementCount,
        ) {
            Text("increment")
        }
    }
}