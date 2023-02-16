package com.example.cft.ui.main

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import com.example.cft.R
import com.example.cft.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding
    private lateinit var cardDigits: String
    private lateinit var emptyField: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardDigits = savedInstanceState?.getString("binDigits") ?: ""
//        val supportActionBar = (activity as AppCompatActivity).supportActionBar!!
//        supportActionBar.title = getString(R.string.search)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emptyField = getString(R.string.empty)
        with(binding) {
            noConnectivityInfo.refresh.setOnClickListener {
                cardInfo.root.visibility = View.GONE
                getSearchInfo(cardDigits)
            }
            searchView.setOnQueryTextListener(object : OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    cardDigits = p0.toString().filter { !it.isWhitespace() }
                    getSearchInfo(cardDigits)
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }

            })
            searchBtn.setOnClickListener {
                cardDigits = searchView.query.toString().filter { !it.isWhitespace() }
                getSearchInfo(cardDigits)
            }
            clearBtn.setOnClickListener {
                searchView.setQuery("", false)
                clearCardFields()
                hideKeyboard(searchView)
            }
            viewModel.cardInfoLiveData.observe(viewLifecycleOwner) {
                with(binding.cardInfo) {
                    binDigits.setText(cardDigits.chunked(4).joinToString(" "))
                    scheme.setText(it?.scheme ?: emptyField)
                    brand.setText(it?.brand ?: emptyField)
                    length.setText(it?.number?.length ?: emptyField)
                    luhn.setText(
                        getYesOrNo(it?.number?.luhn)
                    )
                    type.setText(it?.type ?: emptyField)
                    prepaid.setText(
                        getYesOrNo(it?.prepaid)
                    )
                    country.setText(it?.country?.name ?: emptyField)
                    latitude.setText(it?.country?.latitude ?: emptyField)
                    longitude.setText(it?.country?.longitude ?: emptyField)
                    val nameAndCity =
                        "${it?.bank?.name ?: emptyField}, ${it?.bank?.city ?: emptyField}"
                    address.setText(nameAndCity)
                    site.setText(it?.bank?.url ?: emptyField)
                    phone.setText(it?.bank?.phone ?: emptyField)
                }
            }
            viewModel.errorLiveData.observe(viewLifecycleOwner) {
                if (it != null) {
                    cardInfo.root.visibility = View.GONE
                    noConnectivityInfo.root.visibility = View.VISIBLE
                    if (checkInternetConnectivity()) {
                        binding.noConnectivityInfo.error.text = it.message
                    } else {
                        binding.noConnectivityInfo.error.text =
                            getString(R.string.no_internet_connection)
                    }
                } else {
                    cardInfo.root.visibility = View.VISIBLE
                    noConnectivityInfo.root.visibility = View.GONE
                }
            }
        }
        showMainContent()
    }

    private fun clearCardFields() {
        with(binding.cardInfo) {
            binDigits.setText("")
            scheme.setText(emptyField)
            brand.setText(emptyField)
            length.setText(emptyField)
            luhn.setText(emptyField)
            type.setText(emptyField)
            prepaid.setText(emptyField)
            country.setText(emptyField)
            latitude.setText(emptyField)
            longitude.setText(emptyField)
            address.setText(getString(R.string.two_empty))
            site.setText(emptyField)
            phone.setText(emptyField)
        }
    }

    private fun showMainContent() {
        val isConnected = checkInternetConnectivity()
        with(binding) {
            if (!isConnected) {
                cardInfo.root.visibility = View.GONE
                noConnectivityInfo.root.visibility = View.VISIBLE
                binding.noConnectivityInfo.error.text = getString(R.string.no_internet_connection)
            } else {
                cardInfo.root.visibility = View.VISIBLE
                noConnectivityInfo.root.visibility = View.GONE
            }
        }
    }

    private fun getYesOrNo(it: Boolean?) = if (it != null) {
        if (it) "YES" else "NO"
    } else emptyField

    private fun getSearchInfo(bin: String) {
        if (bin.isNotEmpty()) {
            viewModel.getCardInfoMovies(bin, cardDigits)
        } else {
            clearCardFields()
        }
        hideKeyboard(binding.searchView)
        showMainContent()
    }

    private fun hideKeyboard(searchView: SearchView) {
        activity?.currentFocus?.clearFocus()
        val inputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(searchView.windowToken, 0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("binDigits", cardDigits)
    }

    private fun checkInternetConnectivity(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ))
    }
}