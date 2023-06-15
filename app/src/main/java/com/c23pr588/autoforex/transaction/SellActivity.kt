package com.c23pr588.autoforex.transaction

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.c23pr588.autoforex.MainActivity
import com.c23pr588.autoforex.data.local.UserModel
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.dataStore
import com.c23pr588.autoforex.databinding.ActivitySellBinding
import com.c23pr588.autoforex.viewmodel.PurchaseViewModel
import com.c23pr588.autoforex.viewmodel.SellViewModel
import com.c23pr588.autoforex.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SellActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var binding: ActivitySellBinding
    private lateinit var sellViewModel: SellViewModel
    private lateinit var user: UserModel

    private var sellValue: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSoldCurrency.text = intent.getStringExtra(SellActivity.EXTRA_NAME)

        sellViewModel = ViewModelProvider(
            this@SellActivity,
            ViewModelFactory(UserPreference.getInstance(dataStore), this@SellActivity)
        )[SellViewModel::class.java]

        binding.edSellPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sellValue = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.submitSell.setOnClickListener {
            sellValue = binding.edSellPrice.text.toString().toInt()
            if (sellValue == 0) {
                Toast.makeText(this@SellActivity, "Silakan masukkan harga jual.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                sellViewModel.sellCurrency(sellValue)
                sellViewModel.isLoading.observe(this) {
                    if (it) {
                        binding.progressLogin.visibility = View.VISIBLE
                    } else {
                        binding.progressLogin.visibility = View.GONE
                        Toast.makeText(this@SellActivity, "Telah menjual" + SellActivity.EXTRA_NAME + "seharga IDR" + sellValue.toString(), Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@SellActivity, MainActivity::class.java))
                    }
                }
            }
        }
    }
}