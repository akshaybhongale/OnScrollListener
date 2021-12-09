package com.akshaybhongale.scrollexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akshaybhongale.scrollexample.databinding.AdapterLinearLayoutBinding

class LinearAdapter(private val list: ArrayList<String>) :
    RecyclerView.Adapter<LinearAdapter.LinearLayoutViewHolder>() {
    private var mList = ArrayList<String>()

    init {
        mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearLayoutViewHolder {

        return LinearLayoutViewHolder(
            AdapterLinearLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateList(list: ArrayList<String>, startIndex: Int, endIndex: Int) {
        mList = list
        notifyItemRangeInserted(startIndex, endIndex)
    }

    override fun onBindViewHolder(holder: LinearLayoutViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size

    inner class LinearLayoutViewHolder(private val holder: AdapterLinearLayoutBinding) :
        RecyclerView.ViewHolder(holder.root) {
        fun bind(name: String) {
            holder.txtName.text = name
        }
    }
}