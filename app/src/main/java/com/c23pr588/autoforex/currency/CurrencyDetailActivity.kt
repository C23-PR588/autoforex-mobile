package com.c23pr588.autoforex.currency

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.c23pr588.autoforex.MainActivity
import com.c23pr588.autoforex.data.CurrencyAttributes
import com.c23pr588.autoforex.databinding.ActivityCurrencyDetailBinding
import com.c23pr588.autoforex.transaction.PurchaseActivity
import com.c23pr588.autoforex.transaction.SellActivity

class CurrencyDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_CURRENT_VALUE = "extra_current_value"
    }

    private lateinit var binding: ActivityCurrencyDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDetailName.text = intent.getStringExtra(EXTRA_NAME)
        binding.tvDetailCurrentValue.text = intent.getStringExtra(EXTRA_CURRENT_VALUE)

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this@CurrencyDetailActivity, PurchaseActivity::class.java)
            intent.putExtra(PurchaseActivity.EXTRA_NAME, EXTRA_NAME)
            startActivity(intent)
        }

        binding.btnSell.setOnClickListener {
            val intent = Intent(this@CurrencyDetailActivity, SellActivity::class.java)
            intent.putExtra(SellActivity.EXTRA_NAME, EXTRA_NAME)
            startActivity(intent)
        }
    }
}

//    private fun setupView() {
//        val currency = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra(EXTRA_CURRENCY, CurrencyAttributes::class.java)
//        } else {
//            intent.getParcelableExtra(EXTRA_CURRENCY)
//        }
//
//        if (currency != null) {
//            binding.tvDetailName.text = currency.name
//            binding.tvDetailCurrentValue.text = currency.currentValue.toString()
//        }
//    }
