package com.kadamab.neos.adapter

import Holiday
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadamab.neos.databinding.ItemHolidayListBinding

class HolidayAdapter(
    private val data: List<Holiday>,
) : RecyclerView.Adapter<HolidayAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemHolidayListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemHolidayListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvName.text = data[position].name[1].text
        holder.binding.textType.text = data[position].holidayType
        holder.binding.tvScheduleAt.text = "${data[position].date.day}-${data[position].date.month}-${data[position].date.year}"
    }

    override fun getItemCount(): Int = data.size
}