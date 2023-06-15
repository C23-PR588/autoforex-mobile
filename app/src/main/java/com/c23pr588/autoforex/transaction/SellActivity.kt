package com.c23pr588.autoforex.transaction

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.c23pr588.autoforex.data.local.UserModel
import com.c23pr588.autoforex.databinding.ActivityLoginBinding
import com.c23pr588.autoforex.viewmodel.PurchaseViewModel

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SellActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sellViewModel: SellViewModel
    private lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
    }
}