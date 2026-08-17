package com.example.familyremotesupport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.familyremotesupport.ui.theme.FamilyRemoteSupportTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FamilyRemoteSupportTheme {
                NavGraph()
            }
        }
    }
}
