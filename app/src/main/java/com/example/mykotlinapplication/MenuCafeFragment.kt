package com.example.mykotlinapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.FragmentMenuCafeBinding

class MenuCafeFragment : Fragment() {
    lateinit var model: MenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val binding = DataBindingUtil.inflate<FragmentMenuCafeBinding>(inflater,
            R.layout.fragment_menu_cafe,container,false)
var tipus="postre"
        val spinner: Spinner =binding.spinnermenucafe
        ArrayAdapter.createFromResource(requireContext(),
            R.array.menuprincipal,
            android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        binding.button4.setOnClickListener{view : View ->
            view.findNavController().navigate(R.id.action_menuCafeFragment_to_comandaFragment)
            model = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
            model.sendMessage3(binding.spinnermenucafe.selectedItem.toString())
            var preu= model.getpreu().toFloat().plus(1)
            model.sendPreu(preu.toString())
        }

        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)

        val recyclerView: RecyclerView = binding.rviewt
        recyclerView.layoutManager= LinearLayoutManager(this.activity)
        recyclerView.adapter=MenuAdapter(
            application,
            roomViewModel.primerplat(tipus),
            model
        )

        return binding.root
    }
}