package com.example.mykotlinapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mykotlinapplication.DataBase.ComandaDatabase
import com.example.mykotlinapplication.databinding.FragmentVeureComandasBinding

class VeureComandasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentVeureComandasBinding>(inflater,
            R.layout.fragment_veure_comandas,container,false)

        val application = requireNotNull(this.activity).application
        val dataSource = ComandaDatabase.getInstance(application).comandaDatabaseDao
        val viewModelFactory = RoomViewModelFactory(dataSource, application)
        val roomViewModel = ViewModelProvider(this, viewModelFactory).get(RoomViewModel::class.java)


        binding.textViewVeureComanda.text=roomViewModel.verurecomanda().toString()
        return binding.root
    }
}