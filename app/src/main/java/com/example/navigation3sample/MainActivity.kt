package com.example.navigation3sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.navigation3sample.screen1.Screen1
import com.example.navigation3sample.screen1.Screen1ViewModel
import com.example.navigation3sample.screen2.Screen2
import com.example.navigation3sample.ui.theme.Navigation3SampleTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation3SampleTheme {
                NavRoot()
            }
        }
    }
}

@Serializable
data object Screen1Key : NavKey {
    const val TITLE: String = "Screen1"
}

@Serializable
data class Screen2Key(val count: Int) : NavKey {
    companion object {
        const val TITLE: String = "Screen2"
    }
}


@Composable
fun NavRoot(modifier: Modifier = Modifier) {
    val backStack: SnapshotStateList<NavKey> = rememberNavBackStack(Screen1Key)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Screen1Key> {
                val viewModel = viewModel<Screen1ViewModel>()

                Screen1(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = viewModel,
                    incrementCount = viewModel::incrementCount,
                    navigateTo = { backStack.add(it) },
                )
            }

            entry<Screen2Key> { screen2Args ->
                val count = screen2Args.count
                Screen2(
                    modifier = Modifier.fillMaxSize(),
                    count = count,
                )
            }
        }
    )
}
