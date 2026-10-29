package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TasbihScreen(
    count: Int,
    rounds: Int,
    roundSize: Int,
    onIncrement: () -> Unit,
    onReset: () -> Unit,
    onRoundSizeSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Tasbih", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text("Rounds completed: $rounds", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(33, 99, 100).forEach { size ->
                    FilterChip(
                        selected = size == roundSize,
                        onClick = { onRoundSizeSelected(size) },
                        label = { Text("$size") }
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Text("$count", style = MaterialTheme.typography.displayLarge)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onIncrement) { Text("+1") }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onReset) { Text("Reset") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TasbihScreenPreview() {
    TasbihScreen(
        count = 12,
        rounds = 2,
        roundSize = 33,
        onIncrement = {},
        onReset = {},
        onRoundSizeSelected = {}
    )
}