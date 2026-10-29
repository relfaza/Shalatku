package com.example.shalatku

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SalatKuViewModel : ViewModel() {
    var tasbihCount by mutableIntStateOf(0)
        private set

    var completedRounds by mutableIntStateOf(0)
        private set

    var roundSize by mutableIntStateOf(33)
        private set

    private val _donePrayers = mutableStateListOf<String>()
    val donePrayers: List<String> get() = _donePrayers

    fun incrementTasbih() {
        tasbihCount++
        if (tasbihCount == roundSize) {
            completedRounds++
            tasbihCount = 0
        }
    }

    fun resetTasbih() {
        tasbihCount = 0
        completedRounds = 0
    }

    @JvmName("changeRoundSize")
    fun setRoundSize(size: Int) {
        roundSize = size
        tasbihCount = 0
    }

    fun togglePrayer(name: String) {
        if (name in _donePrayers) _donePrayers.remove(name) else _donePrayers.add(name)
    }

    fun clearPrayers() {
        _donePrayers.clear()
    }
}