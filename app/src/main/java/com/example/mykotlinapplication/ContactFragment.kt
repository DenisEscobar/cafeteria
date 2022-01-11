package com.example.mykotlinapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mykotlinapplication.R
import com.example.mykotlinapplication.databinding.FragmentContactBinding
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity


class ContactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentContactBinding>(inflater,
            R.layout.fragment_contact,container,false)
        (activity as AppCompatActivity).supportActionBar?.title="Contacto"
binding.textViewviewtelf.setOnClickListener { binding.textViewTelefono.callOnClick() }
binding.textViewTelefono.setOnClickListener {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.setData(Uri.parse("tel:"+binding.textViewTelefono.text.toString()));
    startActivity(intent)
}
binding.textViewviewemail.setOnClickListener { binding.textViewCorreo.callOnClick() }
binding.textViewCorreo.setOnClickListener {
    val mailClient = Intent(Intent.ACTION_VIEW)
    mailClient.setType("message/rfc822")
    mailClient.setData(Uri.parse("mailto:"+binding.textViewCorreo.text.toString()))
    startActivity(mailClient)
}
        return binding.root
    }
}