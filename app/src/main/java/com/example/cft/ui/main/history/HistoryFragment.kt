package com.example.cft.ui.main.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cft.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var historyListAdapter: HistoryAdapter
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        historyListAdapter = HistoryAdapter()
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = historyListAdapter
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAllCardsInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.cardInfoLiveData.observe(viewLifecycleOwner) {
            historyListAdapter.setItems(it)
        }
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.clearAllCards.setOnClickListener {
            viewModel.deleteAllCards()
        }
    }
}