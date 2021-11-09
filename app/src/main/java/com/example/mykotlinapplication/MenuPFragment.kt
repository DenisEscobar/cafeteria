package com.example.mykotlinapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mykotlinapplication.databinding.FragmentMenuPBinding
class MenuPFragment : Fragment() {
    lateinit var model: menuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMenuPBinding>(inflater,
            R.layout.fragment_menu_p,container,false)

        val spinner: Spinner =binding.spinner2
        ArrayAdapter.createFromResource(requireContext(),
            R.array.menuprincipal,
            android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.textViewsigientemenu.setOnClickListener { view : View ->
                view.findNavController().navigate(R.id.action_menuPFragment_to_menuSegundoFragment)
            }
        binding.button2.setOnClickListener {view : View ->
            view.findNavController().navigate(R.id.action_menuPFragment_to_menuSegundoFragment)
            model = ViewModelProvider(requireActivity()).get(menuViewModel::class.java)
            model.sendMessage(binding.spinner2.selectedItem.toString())

        }
        setHasOptionsMenu(true)
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}