package com.zerdasoftware.nutrientsbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zerdasoftware.nutrientsbook.R
import com.zerdasoftware.nutrientsbook.databinding.NutrientRecyclerRowBinding
import com.zerdasoftware.nutrientsbook.model.Nutrient
import com.zerdasoftware.nutrientsbook.view.NutrientsListFragmentDirections
import kotlinx.android.synthetic.main.nutrient_recycler_row.view.*

class NutrientRecyclerAdapter(val NutrientList : ArrayList<Nutrient>) :RecyclerView.Adapter<NutrientRecyclerAdapter.NutrientViewHolder>(),NutrientClickListener {

    class NutrientViewHolder(var view:NutrientRecyclerRowBinding) :RecyclerView.ViewHolder(view.root){

    }

    override fun nutrientTapped(view: View) {
        val uuid = view.nutrientID.text.toString().toIntOrNull()
        uuid?.let {
            val action = NutrientsListFragmentDirections.actionNutrientsListFragmentToNutrientsDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NutrientRecyclerRowBinding>(inflater,R.layout.nutrient_recycler_row,parent,false )
        return NutrientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return NutrientList.size
    }

    override fun onBindViewHolder(holder: NutrientViewHolder, position: Int) {
        holder.view.nutrientRow = NutrientList[position]
        holder.view.listener = this


        /*
        holder.itemView.textViewNutrientTitleRow.text = NutrientList.get(position).nutrientTitle
        holder.itemView.textViewNutrientCalorieRow.text = NutrientList.get(position).nutrientCalorie
        holder.itemView.setOnClickListener {
            val action = NutrientsListFragmentDirections.actionNutrientsListFragmentToNutrientsDetailFragment(NutrientList.get(position).uuid)
            Navigation.findNavController(it).navigate(action )
        }
        NutrientList.get(position).nutrientImage?.let {
            holder.itemView.imageViewNutrientRow.fetchImage(
                it,CreatePlaceholder(holder.itemView.context))
        }
         */
    }

    fun NutrientListUpdate(newNutrientList: List<Nutrient>){
        NutrientList.clear()
        NutrientList.addAll(newNutrientList)
        notifyDataSetChanged()
    }


}