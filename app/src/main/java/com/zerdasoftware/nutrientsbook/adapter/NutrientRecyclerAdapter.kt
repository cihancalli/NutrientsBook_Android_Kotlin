package com.zerdasoftware.nutrientsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.model.Nutrient
import com.zerdasoftware.nutrientsbook.view.NutrientsListFragmentDirections
import kotlinx.android.synthetic.main.nutrient_recycler_row.view.*

class NutrientRecyclerAdapter(val NutrientList : ArrayList<Nutrient>) :RecyclerView.Adapter<NutrientRecyclerAdapter.NutrientViewHolder>() {
    class NutrientViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.nutrient_recycler_row,parent,false)
        return NutrientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return NutrientList.size
    }

    override fun onBindViewHolder(holder: NutrientViewHolder, position: Int) {
        holder.itemView.textViewNutrientTitleRow.text = NutrientList.get(position).nutrientTitle
        holder.itemView.textViewNutrientCalorieRow.text = NutrientList.get(position).nutrientCalorie
        holder.itemView.setOnClickListener {
            val action = NutrientsListFragmentDirections.actionNutrientsListFragmentToNutrientsDetailFragment(0)
            Navigation.findNavController(it).navigate(action )
        }
    }

    fun NutrientListUpdate(newNutrientList: List<Nutrient>){
        NutrientList.clear()
        NutrientList.addAll(newNutrientList)
        notifyDataSetChanged()
    }
}