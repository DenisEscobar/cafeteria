package com.example.mykotlinapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.Comanda
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.FragmentVeureComandasBinding
import com.example.mykotlinapplication.sharedPref.SharedApp

class VeureComandasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentVeureComandasBinding>(inflater,
            R.layout.fragment_veure_comandas,container,false)
        (activity as AppCompatActivity).supportActionBar?.title="Historial Comandas"
        binding.setLifecycleOwner(this)
        val application = requireNotNull(this.activity).application
        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)


        //binding.textViewVeureComanda.text=roomViewModel.verurecomanda().toString()

        val recyclerView: RecyclerView = binding.recycleView
        recyclerView.layoutManager= LinearLayoutManager(this.activity)
        recyclerView.adapter=VeureComandaAdapter(
            application,
            roomViewModel.vercomcli(SharedApp.prefs.name.toString())
        )

        return binding.root
    }

}