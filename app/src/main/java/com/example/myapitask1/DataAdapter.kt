package com.example.myapitask1

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapitask1.models.DataListModelClass

class DataAdapter(private val mList: ArrayList<DataListModelClass>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_data_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        Glide
            .with(holder.flagIV.context)
            .load(ItemsViewModel.flag)
            .centerCrop()
            .into(holder.flagIV);

        // sets the text to the textview from our itemHolder class

        Log.d(TAG, "onBindViewHolder: values = "+
              "\nname="+  ItemsViewModel.name.toString()+
              "\ncurrencyTV="+  ItemsViewModel.currency.toString()+
              "\npopulationTV="+  ItemsViewModel.population.toString()+
              "\nsymbolTV="+  ItemsViewModel.symbol.toString()+
              "\nflagIV="+  ItemsViewModel.flag.toString()
        )
        holder.countryNameTV.setText(ItemsViewModel.name.toString())
        holder.currencyTV.setText(ItemsViewModel.currency)
        holder.populationTV.setText( ItemsViewModel.population.toString())
        holder.symbolTV.setText( ItemsViewModel.symbol.toString())

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val flagIV: ImageView = itemView.findViewById(R.id.flagIV)
        val countryNameTV: TextView = itemView.findViewById(R.id.countryNameTV)
        val populationTV: TextView = itemView.findViewById(R.id.populationTV)
        val currencyTV: TextView = itemView.findViewById(R.id.currencyTV)
        val symbolTV: TextView = itemView.findViewById(R.id.symbolTV)
    }
}