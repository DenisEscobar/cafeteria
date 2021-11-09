package com.example.mykotlinapplication.Principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.FragmentMenuSegundoBinding

class MenuSegundoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMenuSegundoBinding>(
            inflater,
            R.layout.fragment_menu_segundo, container, false
        )
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
        }
        return binding.root
    }
}