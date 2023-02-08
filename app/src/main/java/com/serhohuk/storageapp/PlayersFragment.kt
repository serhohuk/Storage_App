package com.serhohuk.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import com.serhohuk.storageapp.models.Player
import com.serhohuk.storageapp.viewmodel.PlayersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PlayersFragment : BaseFragment() {

    private val viewModel by viewModel<PlayersViewModel> {
        parametersOf(requireArguments().getInt(ARG_TEAM_ID))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            val players by viewModel.players.observeAsState(emptyList())
            PlayersScreen(list = players, onSaveClick = { name, age ->
                viewModel.savePlayer(name, age)
            })
        }
    }

    companion object {
        private const val ARG_TEAM_ID = "argTeamId"

        fun newInstance(
            id: Int
        ): PlayersFragment {
            val fragment = PlayersFragment()
            fragment.arguments = bundleOf(
                ARG_TEAM_ID to id
            )
            return fragment
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayersScreen(
    list: List<Player>,
    onSaveClick: (
        String,
        Int
    ) -> Unit
) {
    var name = remember {
        mutableStateOf("")
    }
    var age = remember {
        mutableStateOf("")
    }
    Scaffold() {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Players", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = age.value.toString(),
                onValueChange = { data ->
                    age.value = data
                },
                placeholder = {
                    Text(text = "Player age")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                placeholder = {
                    Text(text = "Player name")
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onSaveClick(name.value, age.value.toInt())
                }
            ) {
                Text(text = "Save Player")
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                content = {
                    items(list) { player ->
                        PlayerItem(player = player)
                    }
                }
            )
        }
    }
}

@Composable
fun PlayerItem(
    player: Player
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp),
        ) {
            Text(
                text = player.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = player.age.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}