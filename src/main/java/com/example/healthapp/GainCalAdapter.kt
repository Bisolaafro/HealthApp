package com.example.healthapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GainCalAdapter(private val foodList: List<FoodInfo>): RecyclerView.Adapter<newViewHolder>(){
    var accum = 0f
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val gainedItems = layoutInflater.inflate(R.layout.gained_items, parent, false)
        return newViewHolder(gainedItems)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: newViewHolder, position: Int) {
        val foodInfo = foodList[position]

        holder.tvName.text = foodInfo.name
        holder.tvCal.text = "${foodInfo.calories.toString()} calories"
        holder.tvServing.text = "Serving size: ${foodInfo.serving_size_g.toString()}g"
        holder.tvTotalFat.text = "Total Fat: "+ foodInfo.fat_total_g.toString() +"g"
        holder.tvSatFat.text = "Saturated Fat: "+ foodInfo.fat_saturated_g.toString()+"g"
        holder.tvProtein.text = "Protein: "+ foodInfo.protein_g.toString() + "g"
        holder.tvSodium.text = "Sodium: "+ foodInfo.sodium_mg.toString() +"mg"
        holder.tvPotassium.text = "Potassium: "+ foodInfo.potassium_mg.toString()+"mg"
        holder.tvCholesterol.text = "Cholesterol: " + foodInfo.cholesterol_mg.toString() +"mg"
        holder.tvCarbohydrates.text = "Carbohydrates: "+ foodInfo.carbohydrates_total_g.toString() +"g"
        holder.tvFiber.text = "Fiber: "+ foodInfo.fiber_g.toString()+"g"
        holder.tvSugar.text = "Sugar: "+ foodInfo.sugar_g.toString() +"g"

//       holder.btnAddCal.setOnClickListener {
//            val calories = foodInfo.calories
//            accum += calories
//
//        }
    }

}

class newViewHolder(val view: View): RecyclerView.ViewHolder(view){

    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvCal = view.findViewById<TextView>(R.id.tvCal)
    val tvServing = view.findViewById<TextView>(R.id.tvServing)
    val tvTotalFat = view.findViewById<TextView>(R.id.tvTotalFat)
    val tvSatFat = view.findViewById<TextView>(R.id.tvSatFat)
    val tvProtein = view.findViewById<TextView>(R.id.tvProtein)
    val tvSodium = view.findViewById<TextView>(R.id.tvSodium)
    val tvPotassium = view.findViewById<TextView>(R.id.tvPotassium)
    val tvCholesterol = view.findViewById<TextView>(R.id.tvCholesterol)
    val tvCarbohydrates = view.findViewById<TextView>(R.id.tvCarbohydrates)
    val tvFiber = view.findViewById<TextView>(R.id.tvFiber)
    val tvSugar = view.findViewById<TextView>(R.id.tvSugar)
    val btnAddCal = view.findViewById<Button>(R.id.btnAddCal)






}