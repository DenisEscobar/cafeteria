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
                   private val fav: List<platofav>,
                   private val room: RoomViewModel,
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
        holder.nametv.text = " "
        holder.primertv.text = data.NomPlato
        holder.segontv.text = data.DescripcioPlato
        holder.tercertv.text = data.PrecioPlato+" €"
      for(i=0; i<fav.count(); i++){
        val favo = fav[i]
        if(favo.NomUsuari==SharedApp.prefs.name.toString()){
          if(data.NomPlato==favo.NomPlato){
            holder.primertv.color(blue)
          }else{
            holder.primertv.color(black)
          }
        }
      }
       holder.itemView.setOnLongClickListener{
          room.insertfav(data.NomPlato)
       }
        holder.itemView.setOnClickListener { view:View->
            if(data.CategoriaPlato=="beguda"){
                menu.sendMessage(data.NomPlato.toString())
                var preu=data.PrecioPlato!!.toDouble()
                menu.sendPreu(preu.toString())
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
