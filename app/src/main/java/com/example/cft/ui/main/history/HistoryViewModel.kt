package com.example.cft.ui.main.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cft.data.repository.CardRepository
import com.example.cft.domain.entity.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val cardRepository: CardRepository,
) : ViewModel() {

    private var _cardInfoLiveData = MutableLiveData<List<Card>>()
    val cardInfoLiveData: LiveData<List<Card>> = _cardInfoLiveData
    fun getAllCardsInfo() {
        viewModelScope.launch {
            _cardInfoLiveData.value = cardRepository.getAllCards().reversed()
        }
    }

    fun deleteAllCards() {
        cardRepository.deleteAllCard()
        _cardInfoLiveData.value = cardRepository.getAllCards().reversed()
    }
}