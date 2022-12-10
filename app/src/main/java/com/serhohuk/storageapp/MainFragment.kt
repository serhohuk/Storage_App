package com.serhohuk.storageapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

class MainFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            Surface(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(0.6f)
                        .background(Color.LightGray),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    BaseButton(
                        text = "One to one",
                        onClick = {
                            navigateToOtOFragment(requireActivity().supportFragmentManager)
                        }
                    )
                    BaseButton(
                        text = "One to many",
                        onClick = {
                            navigateToOtMFragment(requireActivity().supportFragmentManager)
                        }
                    )
                    BaseButton(
                        text = "Many to many",
                        onClick = {
                            navigateToMtMFragment(requireActivity().supportFragmentManager)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun BaseButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(0.6f),
        onClick = onClick
    ) {
        Text(text = text)
    }
}