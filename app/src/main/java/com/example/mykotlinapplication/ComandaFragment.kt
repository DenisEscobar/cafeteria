package com.example.mykotlinapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.FragmentComandaBinding
import com.example.mykotlinapplication.sharedPref.SharedApp

class ComandaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = DataBindingUtil.inflate<FragmentComandaBinding>(inflater,
            R.layout.fragment_comanda,container,false)
        binding.textView2.setText("Comanda De "+ SharedApp.prefs.name.toString())

        val model = ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
        model.message.observe(viewLifecycleOwner, Observer {
            binding.textViewComanda.text = it
        })
        model.message2.observe(viewLifecycleOwner, Observer {
            binding.textViewComanda2.text = it
        })
        model.message3.observe(viewLifecycleOwner, Observer {
            binding.textViewComanda3.text = it
        })
        model.preu.observe(viewLifecycleOwner, Observer {
            binding.textViewComandaPreu.text = it
        })

        val application = requireNotNull(this.activity).application
        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)

        binding.buttonGuardarComanda.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_comandaFragment_to_menuPFragment)
            roomViewModel.onenviacomanda(SharedApp.prefs.name.toString(),model.getP1(), model.getp2(), model.getP3(), model.getpreu())
        }
        binding.buttonCancelarComanda.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_comandaFragment_to_menuPFragment)
            roomViewModel.oncancelar()
        }

        return binding.root
    }

}