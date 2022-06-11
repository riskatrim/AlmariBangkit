package com.example.almaritest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.almaritest.R
import com.example.almaritest.const.Layout
import com.example.almaritest.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class ClothesCardAdapter(
    private val context: Context?,
    private val layout: Int
) : RecyclerView.Adapter<ClothesCardAdapter.ClothesCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource
    private val dataset = DataSource.clothes

    /**
     * Initialize view elements
     */
    class ClothesCardViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // TODO: Declare and initialize all of the list item UI components
        val imageView: ImageView = view?.findViewById(R.id.clothes_img)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesCardViewHolder {
        // TODO: Use a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.

        // TODO Inflate the layout
        val adapterLayout = when (layout) {
            Layout.GRID -> LayoutInflater.from(parent.context)
                .inflate(R.layout.grid_list_item, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }

        // TODO: Null should not be passed into the view holder. This should be updated to reflect
        //  the inflated layout.
        return ClothesCardViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int =
        dataset.size// TODO: return the size of the data set instead of 0

    override fun onBindViewHolder(holder: ClothesCardViewHolder, position: Int) {
        // TODO: Get the data at the current position
        val item = dataset[position]
        // TODO: Set the image resource for the current dog
        holder.imageView.setImageResource(item.imageResourceId)
    }
}