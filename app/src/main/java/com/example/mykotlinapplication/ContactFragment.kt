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


class ContactFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentContactBinding>(inflater,
            R.layout.fragment_contact,container,false)
binding.textViewTelefono.setOnClickListener {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.setData(Uri.parse("tel:666-666-666"));
    startActivity(intent)
}
binding.textViewCorreo.setOnClickListener {
    val mailClient = Intent(Intent.ACTION_VIEW)
    mailClient.setType("message/rfc822")
    mailClient.setData(Uri.parse("mailto:test@gmail.com"))
    startActivity(mailClient)
}
        return binding.root
    }
}