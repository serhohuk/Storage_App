package com.serhohuk.storageapp.ui.otm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.serhohuk.storageapp.model.Family
import com.serhohuk.storageapp.ui.BaseFragment
import com.serhohuk.storageapp.viewModel.OtMViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtMFragment : BaseFragment() {

    private val viewModel by viewModel<OtMViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            val list by viewModel.members.observeAsState(emptyList())
            OneToManyScreen(
                list = list,
                onItemClick = {
                    navigateToPersonsFragment(requireActivity().supportFragmentManager, it)
                },
                onSaveClick = {
                    viewModel.saveFamily(it)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneToManyScreen(
    list: List<Family>,
    onItemClick: (Int) -> Unit,
    onSaveClick: (String) -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        var familyName by remember {
            mutableStateOf("")
        }
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            text = "One to Many",
            style = TextStyle(fontSize = 24.sp),
            textAlign = TextAlign.Center
        )
        Row() {
//            Column(
//                Modifier.weight(1f),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                TextField(modifier = Modifier.fillMaxWidth(),
//                    value = name,
//                    onValueChange = {
//                        name = it
//                    },
//                    placeholder = {
//                        Text(text = "Name")
//                    })
//                TextField(modifier = Modifier.fillMaxWidth(),
//                    value = surname,
//                    onValueChange = {
//                        surname = it
//                    },
//                    placeholder = {
//                        Text(text = "Surname")
//                    })
//                Button(modifier = Modifier.fillMaxWidth(),
//                    onClick = {
//
//                    }) {
//                    Text(text = "Save Person")
//                }
//            }
            //Spacer(modifier = Modifier.width(20.dp))
            Column(
                Modifier.fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = familyName,
                    onValueChange = {
                        familyName = it
                    },
                    placeholder = {
                        Text(text = "Family name")
                    })
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onSaveClick(familyName)
                    }) {
                    Text(text = "Save Family")
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
            items(list) { family ->
                FamilyItem(family = family, onClick = { id ->
                    onItemClick(id)
                })
            }
        })
    }
}

@Composable
fun FamilyItem(family: Family, onClick: (Int) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick(family.familyId)
            }
    ) {
        Surface(
            Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp),
            color = Color.Transparent
        ) {
            Text(
                text = family.familyName,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun OneToManyPreview() {
    Surface(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        OneToManyScreen(onSaveClick = {}, list = emptyList(), onItemClick = {})
    }
}