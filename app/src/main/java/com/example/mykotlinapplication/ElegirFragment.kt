package com.example.mykotlinapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.mykotlinapplication.databinding.FragmentElegirBinding
import com.example.mykotlinapplication.databinding.FragmentMenuCafeBinding

class ElegirFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentElegirBinding>(inflater,
            R.layout.fragment_elegir,container,false)
        binding.buttonHacerComanda.setOnClickListener {  view : View ->
            view.findNavController().navigate(R.id.action_elegirFragment_to_menuPFragment)
        }
        binding.buttonVerComanda.setOnClickListener {  view : View ->
            view.findNavController().navigate(R.id.action_elegirFragment_to_veureComandasFragment)
        }
        return binding.root
    }

//    @Override
//    fun onBackPressed() {
//        val intent = Intent(this.activity, MainActivity::class.java)
//        startActivity(intent)
//    }
}