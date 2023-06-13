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
import com.c23pr588.autoforex.MainViewModel

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "username")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this@MainActivity,
            ViewModelFactory(UserPreference.getInstance(dataStore), this@MainActivity)
        )[MainViewModel::class.java]

        mainViewModel.getIsLogin().observe(this) {
            if (it == false) {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                this@MainActivity.finish()
            }
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvCurrencies.layoutManager = layoutManager

        mainViewModel.listCurrency.observe(this) {
            val adapter = CurrencyAdapter(it)
            binding.rvCurrencies.adapter = adapter
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setCurrenciesData(currencies: List<ListCurrencyItem>) {
        val adapter = CurrencyAdapter(currencies)
        binding.rvCurrencies.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }
}