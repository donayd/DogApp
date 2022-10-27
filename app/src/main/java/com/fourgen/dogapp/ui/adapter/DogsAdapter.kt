package com.dreammkr.pokedex.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.fourgen.dogapp.R

class DogsAdapter(
    private var breedList: List<String>,
    private var breed_name: String
) :
    RecyclerView.Adapter<DogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogsViewHolder(
            layoutInflater.inflate(
                R.layout.item_dog, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DogsViewHolder, id: Int) {
        holder.render(breedList[id], breed_name)
        setAnimation(holder.itemView)
    }

    override fun getItemCount(): Int = breedList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updatePokemons(dogBreeds: List<String>) {
        this.breedList = dogBreeds
        notifyDataSetChanged()
    }

    private fun setAnimation(view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(view.context, R.anim.zoom_in))
    }

}