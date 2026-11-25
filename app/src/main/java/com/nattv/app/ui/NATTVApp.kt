package com.nattv.app.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nattv.app.ui.theme.Gold
import com.nattv.app.ui.theme.Muted
import com.nattv.app.ui.theme.Obsidian
import com.nattv.app.ui.theme.Panel
import com.nattv.app.ui.theme.SoftWhite

private enum class Destination(val label: String) {
    TONIGHT("What's On Tonight"), LIVE("Live TV"), GUIDE("TV Guide"),
    MOVIES("Movies"), SERIES("Series"), FAVORITES("Favorites"),
    SEARCH("Search"), SETTINGS("Settings"), MORE("More")
}

@Composable
fun NATTVApp() {
    val configuration = LocalConfiguration.current
    val isTv = (configuration.uiMode and Configuration.UI_MODE_TYPE_MASK) ==
        Configuration.UI_MODE_TYPE_TELEVISION
    var selectedName by rememberSaveable { mutableStateOf(Destination.TONIGHT.name) }
    val selected = Destination.entries.firstOrNull { it.name == selectedName }
        ?: Destination.TONIGHT

    Surface(Modifier.fillMaxSize(), color = Obsidian, contentColor = SoftWhite) {
        if (isTv) {
            Row(Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.width(230.dp).fillMaxHeight()
                        .background(Panel).padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Brand()
                    Spacer(Modifier.padding(top = 4.dp))
                    Destination.entries.forEach { destination ->
                        TextButton(
                            onClick = { selectedName = destination.name },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (selected == destination) "◆  ${destination.label}" else destination.label,
                                color = if (selected == destination) Gold else SoftWhite
                            )
                        }
                    }
                }
                ScreenContent(selected, Modifier.weight(1f).fillMaxSize()) {
                    selectedName = it.name
                }
            }
        } else {
            Column(Modifier.fillMaxSize()) {
                ScreenContent(selected, Modifier.weight(1f).fillMaxWidth()) {
                    selectedName = it.name
                }
                NavigationBar(containerColor = Panel) {
                    val phoneDestinations = listOf(
                        Destination.TONIGHT, Destination.LIVE, Destination.GUIDE,
                        Destination.FAVORITES, Destination.MORE
                    )
                    phoneDestinations.forEach { destination ->
                        NavigationBarItem(
                            selected = selected == destination,
                            onClick = { selectedName = destination.name },
                            icon = {
                                Icon(
                                    imageVector = when (destination) {
                                        Destination.TONIGHT -> Icons.Default.Home
                                        Destination.LIVE -> Icons.Default.LiveTv
                                        Destination.GUIDE -> Icons.Default.ViewWeek
                                        Destination.FAVORITES -> Icons.Default.Favorite
                                        else -> Icons.Default.MoreHoriz
                                    },
                                    contentDescription = destination.label
                                )
                            },
                            label = { Text(destination.label) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Brand() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "N", color = Obsidian, fontWeight = FontWeight.Black,
            modifier = Modifier.background(Gold, RoundedCornerShape(9.dp))
                .padding(horizontal = 11.dp, vertical = 5.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("NAT TV", color = Gold, style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ScreenContent(
    destination: Destination,
    modifier: Modifier = Modifier,
    onNavigate: (Destination) -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 26.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (destination == Destination.TONIGHT) Brand()
        Text(destination.label, style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold)
        Text(
            text = when (destination) {
                Destination.TONIGHT -> "Your evening entertainment, gathered in one place."
                Destination.LIVE -> "Your live channels will appear here after an IPTV account is added."
                Destination.GUIDE -> "Browse what's live now and what's coming up in your TV guide."
                Destination.MOVIES -> "Your movie library will appear here."
                Destination.SERIES -> "Your series and episodes will appear here."
                Destination.FAVORITES -> "Your favorites from all connected accounts will appear here."
                Destination.SEARCH -> "Search live TV, movies, series and program listings."
                Destination.SETTINGS -> "Manage accounts, playback, appearance and parental controls."
                Destination.MORE -> "More from NAT TV"
            },
            color = Muted, style = MaterialTheme.typography.bodyLarge
        )
        if (destination == Destination.MORE) {
            listOf(Destination.MOVIES, Destination.SERIES, Destination.SEARCH,
                Destination.SETTINGS).forEach { item ->
                TextButton(onClick = { onNavigate(item) }) {
                    Text(item.label, color = Gold, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
        if (destination == Destination.TONIGHT) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Panel),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("WELCOME TO NAT TV", color = Gold,
                        style = MaterialTheme.typography.labelLarge)
                    Text("Your entertainment hub is ready for its first connection.",
                        color = SoftWhite, style = MaterialTheme.typography.titleMedium)
                    Text("Next: add an authorized M3U playlist or Xtream Codes account.",
                        color = Muted)
                }
            }
        }
    }
}