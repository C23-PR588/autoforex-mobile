package com.c23pr588.autoforex.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c23pr588.autoforex.data.traffic.ListCurrencyItem
import com.c23pr588.autoforex.databinding.CurrencyCardBinding

class CurrencyAdapter : ListAdapter<ListCurrencyItem, CurrencyAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CurrencyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currenciesData = getItem(position)
        holder.bind(currenciesData)
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

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ListCurrencyItem> =
            object : DiffUtil.ItemCallback<ListCurrencyItem>(){
                override fun areItemsTheSame(oldItem: ListCurrencyItem, newItem: ListCurrencyItem): Boolean {
                    return oldItem.name == newItem.name
                }
                override fun areContentsTheSame(oldItem: ListCurrencyItem, newItem: ListCurrencyItem): Boolean {
                    return oldItem.currentValue == newItem.currentValue
                }
            }
    }
}