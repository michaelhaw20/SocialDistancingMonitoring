package com.example.socialdistancing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.socialdistancing.databinding.ItemListBinding

class EntryAdapter: RecyclerView.Adapter<EntryAdapter.RecyclerViewHolder>(){
    var entry = ArrayList<Entry>()
    private var onItemClickCallback: OnItemClickCallback? = null

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = ItemListBinding.bind(view)
        fun bind(entry : Entry){
            with(itemView){
                Glide.with(binding.ListView.context)
                        .load(entry.url)
                        .apply(RequestOptions())
                        .into(binding.image)
                binding.ListView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(entry.url.toString())
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(entry[position])
    }

    override fun getItemCount(): Int = entry.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataku: String)
    }

    fun setData(items: ArrayList<Entry>) {
        entry.clear()
        entry.addAll(items)
        notifyDataSetChanged()
    }
}