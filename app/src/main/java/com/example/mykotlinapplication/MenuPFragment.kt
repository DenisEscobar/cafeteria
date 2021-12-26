package com.example.mykotlinapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.FragmentMenuPBinding
class MenuPFragment : Fragment() {
    lateinit var model: MenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentMenuPBinding>(inflater,
            R.layout.fragment_menu_p,container,false)
var tipus="beguda"
        /*
        val spinner: Spinner =binding.spinner2
        ArrayAdapter.createFromResource(requireContext(),
            R.array.menuprincipal1,
            android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


        binding.textViewsigientemenu.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_menuPFragment_to_menuSegundoFragment)
            }

        binding.button2.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_menuPFragment_to_menuSegundoFragment)
            model.sendMessage(binding.spinner2.selectedItem.toString())
            model.sendPreu("2")
        }
        */

        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)

        val recyclerView: RecyclerView = binding.rviewp
        recyclerView.layoutManager= LinearLayoutManager(this.activity)
        recyclerView.adapter=MenuAdapter(
            application,
            roomViewModel.primerplat(tipus),
            model
        )

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}