package com.serhohuk.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import com.serhohuk.storageapp.models.Team
import com.serhohuk.storageapp.viewmodel.TeamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtMFragment : BaseFragment() {

    private val viewModel by viewModel<TeamViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            val teams by viewModel.teams.observeAsState(emptyList())
            TeamScreen(list = teams, onSaveClick = {
                viewModel.saveTeam(it)
            }, onItemClick = {
                navigateToPlayersFragment(
                    requireActivity().supportFragmentManager,
                    it
                )
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(
    list: List<Team>,
    onSaveClick: (
        String
    ) -> Unit,
    onItemClick: (
        Int
    ) -> Unit
) {
    var teamName = remember {
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
            Text(text = "One to many", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(24.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = teamName.value,
                onValueChange = {
                    teamName.value = it
                },
                placeholder = {
                    Text(text = "Team")
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onSaveClick(teamName.value)
                }
            ) {
                Text(text = "Save Team")
            }
            Spacer(Modifier.height(16.dp))
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                content = {
                    items(list) { team ->
                        TeamItem(team = team, onClick = { id ->
                            onItemClick(id)
                        })
                    }
                }
            )
        }
    }
}

@Composable
fun TeamItem(
    team: Team,
    onClick: (
        Int
    ) -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick(team.teamId)
            }
    ) {
        Surface(
            Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp),
            color = Color.Transparent
        ) {
            Text(
                text = team.name,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}