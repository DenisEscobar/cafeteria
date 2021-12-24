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
import com.example.mykotlinapplication.databinding.FragmentMenuSegundoBinding

class MenuSegundoFragment : Fragment() {
    lateinit var model: MenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMenuSegundoBinding>(
            inflater,
            R.layout.fragment_menu_segundo, container, false
        )
var tipus="entrepan"
        val spinner: Spinner =binding.spinnermenu2
        ArrayAdapter.createFromResource(requireContext(),
            R.array.menuprincipal,
            android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        binding.textViewsegundoplato.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_menuSegundoFragment_to_menuCafeFragment)
        }
        binding.button3.setOnClickListener{view: View ->
            view.findNavController().navigate(R.id.action_menuSegundoFragment_to_menuCafeFragment)
            model = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
            model.sendMessage2(binding.spinnermenu2.selectedItem.toString())
            var preu=model.getpreu().toFloat().plus(4)
            model.sendPreu(preu.toString())
        }

        val application = requireNotNull(this.activity).application
        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)

        val recyclerView: RecyclerView = binding.rviews
        recyclerView.layoutManager= LinearLayoutManager(this.activity)
        recyclerView.adapter=MenuAdapter(
            application,
            roomViewModel.primerplat(tipus),
            model
        )

        return binding.root
    }
}