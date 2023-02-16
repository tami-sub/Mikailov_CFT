package com.example.cft.ui.main.history

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cft.databinding.ItemCardInfoBinding
import com.example.cft.domain.entity.Card

class HistoryAdapter() : RecyclerView.Adapter<HistoryAdapter.CardViewHolder>() {

    private val emptyField = "-"
    private var cardsList = mutableListOf<Card>()

    inner class CardViewHolder(private val binding: ItemCardInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(card: Card) {
            binding.also { x ->
                x.binDigits
                x.binDigits.setText(card.cardNumber)
                x.scheme.setText(card.scheme ?: emptyField)
                x.brand.setText(card.brand ?: emptyField)
                x.length.setText(card.number?.length ?: emptyField)
                x.luhn.setText(
                    getYesOrNo(card.number?.luhn)
                )
                x.type.setText(card.type ?: emptyField)
                x.prepaid.setText(
                    getYesOrNo(card.prepaid)
                )
                x.country.setText(card.country?.name ?: emptyField)
                x.latitude.setText(card.country?.latitude ?: emptyField)
                x.longitude.setText(card.country?.longitude ?: emptyField)
                val nameAndCity =
                    "${card.bank?.name ?: emptyField}, ${card.bank?.city ?: emptyField}"
                x.address.setText(nameAndCity)
                x.site.setText(card.bank?.url ?: emptyField)
                x.phone.setText(card.bank?.phone ?: emptyField)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCardInfoBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        cardsList.getOrNull(position)?.let { card ->
            holder.bind(card)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(cards: List<Card>) {
        cardsList = cards.toMutableList()
        notifyDataSetChanged()
    }

    private fun getYesOrNo(it: Boolean?) = if (it != null) {
        if (it) "YES" else "NO"
    } else emptyField

    override fun getItemCount(): Int = cardsList.size
}