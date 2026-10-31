package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrayerTimesSection(
    state: PrayerTimesUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Text("Prayer times", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        when (state) {
            PrayerTimesUiState.Loading -> {
                CircularProgressIndicator()
            }
            is PrayerTimesUiState.Success -> {
                state.times.forEach { prayer ->
                    Row(
                        Modifier.fillMaxWidth().padding(vertical = 2.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(prayer.name)
                        Text(prayer.time)
                    }
                }
                Spacer(Modifier.height(8.dp))
                Button(onClick = onRefresh) { Text("Refresh") }
            }
            is PrayerTimesUiState.Error -> {
                Text(state.message, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(8.dp))
                Button(onClick = onRefresh) { Text("Retry") }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionLoadingPreview() {
    PrayerTimesSection(state = PrayerTimesUiState.Loading, onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionSuccessPreview() {
    PrayerTimesSection(state = PrayerTimesUiState.Success(samplePrayers), onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionErrorPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Error("Could not load prayer times. Please try again."),
        onRefresh = {}
    )
}