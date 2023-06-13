package com.c23pr588.autoforex.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c23pr588.autoforex.R
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

    override fun getItemCount(): Int = listCurrency.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currency = listCurrency[position]

        holder.binding.tvName.text = currency.name
        holder.binding.tvCurrentValue.text = currency.currentValue.toString()
        holder.binding.imgItemPhoto.setImageResource(R.drawable.moneybag)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCurrency[holder.adapterPosition])
        }


    }

//    interface OnItemClickCallback {
//        fun onItemClicked(listCurrencyItem: ListCurrencyItem) {
//
//        }
//    }
//}