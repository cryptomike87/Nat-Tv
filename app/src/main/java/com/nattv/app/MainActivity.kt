package com.nattv.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nattv.app.ui.NATTVApp
import com.nattv.app.ui.theme.NATTVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NATTVTheme { NATTVApp() } }
    }
}