package com.c23pr588.autoforex.currency

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.c23pr588.autoforex.R
import com.c23pr588.autoforex.data.traffic.ListCurrencyItem
import com.c23pr588.autoforex.databinding.CurrencyCardBinding

class CurrencyAdapter (private val listCurrency: List<ListCurrencyItem>) : RecyclerView.Adapter<CurrencyAdapter.ListViewHolder>() {
    class ListViewHolder (var binding: CurrencyCardBinding): RecyclerView.ViewHolder(binding.root)

//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
//        this.onItemClickCallback = onItemClickCallback
//    }

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

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(mContext, CurrencyDetailActivity::class.java)
            moveDetail.putExtra(CurrencyDetailActivity.EXTRA_NAME, currency.name)
            moveDetail.putExtra(CurrencyDetailActivity.EXTRA_CURRENT_VALUE, currency.currentValue.toString())
            mContext.startActivity(moveDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(listCurrencyItem: ListCurrencyItem)
    }
}