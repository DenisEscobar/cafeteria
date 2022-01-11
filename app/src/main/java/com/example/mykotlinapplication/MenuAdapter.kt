package com.example.mykotlinapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.platofav
import com.example.mykotlinapplication.DataBase.platos
import com.example.mykotlinapplication.sharedPref.SharedApp

class MenuAdapter(private val context: Context,
                  private val list: List<platos>,
                  private val fav: List<platofav>,
                  private val menuViewModel: MenuViewModel,
                  private val room: RoomViewModel
                  ) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    val menu:MenuViewModel = menuViewModel
    val roomView:RoomViewModel = room
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nametv: TextView = view.findViewById(R.id.textViewName1)
        val primertv: TextView = view.findViewById(R.id.textViewPrimer1)
        val segontv: TextView = view.findViewById(R.id.textViewSegon1)
        val tercertv: TextView = view.findViewById(R.id.textViewTercer1)
        val fav: Button = view.findViewById(R.id.buttonfav)
        val pedir: Button = view.findViewById(R.id.buttonpedir)
        var id=0
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
      for(i in fav){
        val favo = i
        if(favo.NomUsuari== SharedApp.prefs.name.toString()){
          if(data.NomPlato==favo.NomPlato){
              holder.primertv.setTextColor(Color.parseColor("#0000FF"))
              holder.segontv.setTextColor(Color.parseColor("#0000FF"))
              holder.tercertv.setTextColor(Color.parseColor("#0000FF"))
          }
        }
      }
       holder.fav.setOnClickListener{
           var a=0
           for(i in fav) {
               val favo = i
               if (favo.NomUsuari == SharedApp.prefs.name.toString()) {
                   if (data.NomPlato == favo.NomPlato) {
                       a = 1
                   }
               }
           }
           if(a==0 && holder.id==0){
               roomView.insertfav(data.NomPlato.toString())
               holder.primertv.setTextColor(Color.parseColor("#0000FF"))
               holder.segontv.setTextColor(Color.parseColor("#0000FF"))
               holder.tercertv.setTextColor(Color.parseColor("#0000FF"))
               holder.id=1
               a=1
           }
           else if(a==0 && holder.id==1){
               roomView.deletefav(data.NomPlato.toString())
               holder.primertv.setTextColor(Color.parseColor("#000000"))
               holder.segontv.setTextColor(Color.parseColor("#000000"))
               holder.tercertv.setTextColor(Color.parseColor("#000000"))
               holder.id=0
               a=0
           }else if(a==1 && holder.id==0){
               roomView.deletefav(data.NomPlato.toString())
               holder.primertv.setTextColor(Color.parseColor("#000000"))
               holder.segontv.setTextColor(Color.parseColor("#000000"))
               holder.tercertv.setTextColor(Color.parseColor("#000000"))
               holder.id=1
               a=0
           }
           else if(a==1 && holder.id==1){
               roomView.insertfav(data.NomPlato.toString())
               holder.primertv.setTextColor(Color.parseColor("#0000FF"))
               holder.segontv.setTextColor(Color.parseColor("#0000FF"))
               holder.tercertv.setTextColor(Color.parseColor("#0000FF"))
               holder.id=0
               a=1
           }
       }
        holder.pedir.setOnClickListener { view:View->
            if(data.CategoriaPlato=="beguda"){
                menu.sendMessage(data.NomPlato.toString())
                var preu=data.PrecioPlato!!.toDouble()
                menu.sendPreu(preu.toString())
                view.findNavController().navigate(R.id.action_menuPFragment_to_menuSegundoFragment)
            }
            if(data.CategoriaPlato=="entrepan"){
                view.findNavController().navigate(R.id.action_menuSegundoFragment_to_menuCafeFragment)
                menu.sendMessage2(data.NomPlato.toString())
                menu.sendPreu2(data.PrecioPlato.toString())
            }
            if(data.CategoriaPlato=="postre"){
                view.findNavController().navigate(R.id.action_menuCafeFragment_to_comandaFragment)
                menu.sendMessage3(data.NomPlato.toString())
                menu.sendPreu3(data.PrecioPlato.toString())
            }

        }
    }

}
