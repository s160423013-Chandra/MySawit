package com.example.mysawit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysawit.R
import com.example.mysawit.databinding.FragmentLoginBinding
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = view.findViewById<EditText>(R.id.txtUsername)
        val password = view.findViewById<EditText>(R.id.txtPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            if (username.text.toString() == "student" &&
                password.text.toString() == "123") {

                findNavController().navigate(R.id.actionDashboardFragment)

            } else {
                Toast.makeText(requireContext(), "Login gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

}