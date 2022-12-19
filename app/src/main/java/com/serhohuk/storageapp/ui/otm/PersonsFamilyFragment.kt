package com.serhohuk.storageapp.ui.otm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.serhohuk.storageapp.model.Person
import com.serhohuk.storageapp.ui.BaseFragment
import com.serhohuk.storageapp.viewModel.PersonsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PersonsFamilyFragment : BaseFragment() {

    private val viewModel by viewModel<PersonsViewModel> {
        parametersOf(requireArguments().getInt(ARG_FAMILY_ID,0))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            val list by viewModel.members.observeAsState(emptyList())
            PersonScreen(list = list, onSaveClick = { name, surname ->
                viewModel.saveFamilyPerson(name, surname)
            })
        }
    }

    companion object {
        private const val ARG_FAMILY_ID = "argFamilyId"

        fun newInstance(id: Int): PersonsFamilyFragment {
            val fragment = PersonsFamilyFragment()
            fragment.arguments = bundleOf(
                ARG_FAMILY_ID to id
            )
            return fragment
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonScreen(
    list: List<Person>,
    onSaveClick: (String, String) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        var name by remember {
            mutableStateOf("")
        }

        var surname by remember {
            mutableStateOf("")
        }
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            text = "Family members",
            style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center
        )
        Row() {
            Column(
                Modifier.fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    placeholder = {
                        Text(text = "Name")
                    })
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = surname,
                    onValueChange = {
                        surname = it
                    },
                    placeholder = {
                        Text(text = "Surname")
                    })
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSaveClick(name, surname)
                    }) {
                    Text(text = "Save Person")
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
            items(list) { person ->
                PersonItem(person = person)
            }
        })
    }
}

@Composable
fun PersonItem(person: Person) {
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
                text = person.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = person.surname,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


