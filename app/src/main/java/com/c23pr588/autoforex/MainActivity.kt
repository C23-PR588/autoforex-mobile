package com.c23pr588.autoforex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.c23pr588.autoforex.currency.CurrencyAdapter
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.data.traffic.ListCurrencyItem
import com.c23pr588.autoforex.databinding.ActivityMainBinding
import com.c23pr588.autoforex.login.LoginActivity
import com.c23pr588.autoforex.viewmodel.ViewModelFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "username")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvCurrencies.layoutManager = layoutManager

        mainViewModel.listCurrency.observe(this) {
            val adapter = CurrencyAdapter()
            binding.rvCurrencies.adapter = adapter
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setCurrenciesData(currencies: List<ListCurrencyItem>) {
        val adapter = CurrencyAdapter()
        binding.rvCurrencies.adapter = adapter
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }
}