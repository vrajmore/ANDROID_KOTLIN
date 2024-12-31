package com.example.deviceinventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Item(
    val fir:String,
    val las:String,
    val dev:String
)
class myadapter(private val items: MutableList<Item>) : RecyclerView.Adapter<myadapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val device: TextView = itemView.findViewById(R.id.textView)
        val firstname: TextView = itemView.findViewById(R.id.textView3)
        val lastname: TextView = itemView.findViewById(R.id.textView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.device.text = "${item.dev}"
        holder.firstname.text = "${item.fir}"
        holder.lastname.text = "${item.las}"
    }

    override fun getItemCount(): Int = items.size
    fun updateData(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}
