package com.example.cft.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cft.data.repository.BinRepository
import com.example.cft.data.repository.CardRepository
import com.example.cft.domain.entity.Card
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val binRepository: BinRepository,
    private val cardRepository: CardRepository,
) : ViewModel() {

    private var _cardInfoLiveData = MutableLiveData<Card?>()
    val cardInfoLiveData: LiveData<Card?> = _cardInfoLiveData

    private var _errorLiveData = MutableLiveData<Throwable?>()
    val errorLiveData: LiveData<Throwable?> = _errorLiveData

    fun getCardInfoMovies(bin: String, cardDigits: String) {
        viewModelScope.launch {
            binRepository.getCardInfo(bin).onSuccess {
                _cardInfoLiveData.value = it
                it.cardNumber = cardDigits
                cardRepository.saveCard(it)
                _errorLiveData.value = null
            }.onFailure {
                _cardInfoLiveData.value = null
                _errorLiveData.value = it
            }
        }
    }
}