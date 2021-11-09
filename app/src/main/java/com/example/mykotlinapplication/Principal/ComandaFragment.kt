package com.example.mykotlinapplication.Principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.FragmentComandaBinding

class ComandaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentComandaBinding>(inflater,
            R.layout.fragment_comanda,container,false)
        binding.textView2.setText("Comanda")
        binding.textView2.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_comandaFragment_to_menuPFragment)
        }
        val model = ViewModelProvider(requireActivity()).get(menuViewModel::class.java)
        model.message.observe(viewLifecycleOwner, Observer {
            binding.textViewComanda.text = it
        })
        model.message2.observe(viewLifecycleOwner, Observer {
            binding.textViewComanda2.text = it
        })
        model.message3.observe(viewLifecycleOwner, Observer {
            binding.textViewComanda3.text = it
        })

        return binding.root
    }

}