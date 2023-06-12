package com.c23pr588.autoforex.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.c23pr588.autoforex.data.traffic.ListCurrencyItem
import com.c23pr588.autoforex.databinding.CurrencyCardBinding

class CurrencyAdapter (private val listCurrency: List<ListCurrencyItem>) : RecyclerView.Adapter<CurrencyAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder (var binding: CurrencyCardBinding): RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = CurrencyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currency = listCurrency(position)

        holder.binding.tvName.text = currency.
        
    }

    inner class MyViewHolder(private val binding: CurrencyCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currenciesData: ListCurrencyItem){
            binding.apply {
                tvName.text = currenciesData.name
                tvCurrentValue.text = currenciesData.currentValue.toString()
                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(currenciesData)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(listCurrencyItem: ListCurrencyItem)
    }
}