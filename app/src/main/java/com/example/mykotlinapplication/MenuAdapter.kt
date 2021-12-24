package com.example.mykotlinapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.platos

class MenuAdapter (private val context: Context,
                   private val list: List<platos>,
                   private val menuViewModel: MenuViewModel) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    val menu:MenuViewModel = menuViewModel
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nametv: TextView = view.findViewById(R.id.textViewName)
        val primertv: TextView = view.findViewById(R.id.textViewPrimer)
        val segontv: TextView = view.findViewById(R.id.textViewSegon)
        val tercertv: TextView = view.findViewById(R.id.textViewTercer)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
//        holder.nametv.text = "data.IdCliente.toString()"
        holder.primertv.text = data.NomPlato
        holder.segontv.text = data.DescripcioPlato
        holder.tercertv.text = data.PrecioPlato
        holder.itemView.setOnClickListener { view:View->
            if(data.CategoriaPlato=="beguda"){
                menu.sendMessage(data.NomPlato.toString())
                menu.sendPreu((menu.getpreu().toDouble().plus(data.PrecioPlato!!.toDouble())).toString())
                view.findNavController().navigate(R.id.action_menuPFragment_to_menuSegundoFragment)
            }
            if(data.CategoriaPlato=="entrepan"){
                view.findNavController().navigate(R.id.action_menuSegundoFragment_to_menuCafeFragment)
                menu.sendMessage2(data.NomPlato.toString())
                menu.sendPreu((menu.getpreu().toDouble().plus(data.PrecioPlato!!.toDouble())).toString())
            }
            if(data.CategoriaPlato=="postre"){
                view.findNavController().navigate(R.id.action_menuCafeFragment_to_comandaFragment)
                menu.sendMessage3(data.NomPlato.toString())
                menu.sendPreu((menu.getpreu().toDouble().plus(data.PrecioPlato!!.toDouble())).toString())
            }

        }
    }

}