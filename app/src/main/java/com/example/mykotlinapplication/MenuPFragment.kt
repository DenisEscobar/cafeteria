package com.example.mykotlinapplication

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.FragmentMenuPBinding
import com.example.mykotlinapplication.sharedPref.SharedApp

class MenuPFragment : Fragment() {
    lateinit var model: MenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentMenuPBinding>(
            inflater,
            R.layout.fragment_menu_p, container, false
        )
        (activity as AppCompatActivity).supportActionBar?.title="Bebidas"
        var tipus = "beguda"
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
binding.textView.text="Bebidas"
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)
        binding.setLifecycleOwner(this)
        val recyclerView: RecyclerView = binding.rviewp
        recyclerView.layoutManager = LinearLayoutManager(this.activity)
        recyclerView.adapter = MenuAdapter(
            application,
            roomViewModel.primerplat(tipus),
            roomViewModel.getfav(SharedApp.prefs.name.toString()),
            model,
            roomViewModel
        )

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
//    @Override
//    fun onBackPressed() {
//        val application = requireNotNull(this.activity).application
//        AlertDialog.Builder(application)
//            .setMessage("You will lose command, are you sure?")
//            .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
//
//            }
//            .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->
//
//            }
//            .show()
//    }
}
