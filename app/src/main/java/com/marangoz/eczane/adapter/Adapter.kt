package com.marangoz.eczane.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.marangoz.eczane.R
import com.marangoz.eczane.model.Result


class Adapter(val mContext: Context?) : RecyclerView.Adapter<Adapter.ViewHolderClass>() {

    var resultlist : List<Result> = listOf()
    val option : Bundle? = Bundle()

    inner class ViewHolderClass(view: View) :RecyclerView.ViewHolder(view){
        var nameText: TextView
        var addressText : TextView
        var phoneText : TextView
        var mapButton : Button
        init {
            nameText = view.findViewById(R.id.nameText)
            addressText = view.findViewById(R.id.addressText)
            phoneText = view.findViewById(R.id.telText)
            mapButton = view.findViewById(R.id.openMapButton)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val design = LayoutInflater.from(mContext).inflate(R.layout.adapter_design, parent, false)
        return ViewHolderClass(design)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        val result = resultlist[position]


        holder.nameText.text = result.name
        holder.addressText.text = result.address
        holder.phoneText.text = result.phone

        holder.mapButton.setOnClickListener(){
            val gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=" + result.loc)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mContext != null) {
                startActivity(mContext,mapIntent,option)
            }


        }


    }


    override fun getItemCount(): Int {
        return resultlist.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Result>){
        resultlist= list
        notifyDataSetChanged()
    }
}