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
import com.c23pr588.autoforex.currency.CurrencyDetailActivity
import com.c23pr588.autoforex.data.local.UserModel
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.dataStore
import com.c23pr588.autoforex.databinding.ActivityLoginBinding
import com.c23pr588.autoforex.databinding.ActivityPurchaseBinding
import com.c23pr588.autoforex.viewmodel.LoginViewModel
import com.c23pr588.autoforex.viewmodel.PurchaseViewModel
import com.c23pr588.autoforex.viewmodel.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class PurchaseActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var binding: ActivityPurchaseBinding
    private lateinit var purchaseViewModel: PurchaseViewModel
    private lateinit var user: UserModel

    private var purchaseValue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPurchasedCurrency.text = intent.getStringExtra(PurchaseActivity.EXTRA_NAME)

        purchaseViewModel = ViewModelProvider(
            this@PurchaseActivity,
            ViewModelFactory(UserPreference.getInstance(dataStore), this@PurchaseActivity)
        )[PurchaseViewModel::class.java]

        binding.edPurchasePrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                purchaseValue = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.submitPurchase.setOnClickListener {
            purchaseValue = binding.edPurchasePrice.text.toString().toInt()
            if (purchaseValue == 0) {
                Toast.makeText(this@PurchaseActivity, "Silakan masukkan harga beli.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                purchaseViewModel.purchaseCurrency(purchaseValue)
                purchaseViewModel.isLoading.observe(this) {
                    if (it) {
                        binding.progressLogin.visibility = View.VISIBLE
                    } else {
                        binding.progressLogin.visibility = View.GONE
                        Toast.makeText(this@PurchaseActivity, "Telah membeli" + EXTRA_NAME + "seharga IDR" + purchaseValue.toString(), Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@PurchaseActivity, MainActivity::class.java))
                    }
                }
            }
        }
    }
}