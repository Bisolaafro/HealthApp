package com.example.healthapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class healthAdapter(private val calList: List<Calories>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val burnedItems = layoutInflater.inflate(R.layout.burned_items, parent, false)
        return MyViewHolder(burnedItems)
    }

    override fun getItemCount(): Int {
        return calList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDescription.text= calList[position].name
        holder.tvCal.text = calList[position].total_calories.toString()
//        holder.btnCal.setOnClickListener {
//            val calories = calList[position].total_calories
//            activityFragment.accumulatedCalories += calories
//        }


    }

}

class MyViewHolder(private val view: View): RecyclerView.ViewHolder(view){
    val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
    val tvCal = view.findViewById<TextView>(R.id.tvCalBurn)
    val tvW = view.findViewById<TextView>(R.id.tvW)
    //val btnCal = view.findViewById<Button>(R.id.btnCal)

}