package com.example.mykotlinapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.Comanda

class VeureComandaAdapter(private val context: Context,
                          private val list: List<Comanda>) : RecyclerView.Adapter<VeureComandaAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nametv: TextView = view.findViewById(R.id.textViewName1)
        val primertv: TextView = view.findViewById(R.id.textViewPrimer1)
        val segontv: TextView = view.findViewById(R.id.textViewSegon1)
        val tercertv: TextView = view.findViewById(R.id.textViewTercer1)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_ver,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.nametv.text = data.preciototal+" €"
        holder.primertv.text = data.primerplato
        holder.segontv.text = data.segundoplato
        holder.tercertv.text = data.tercerplato
    }

}