package com.example.mysawit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mysawit.R
import com.example.mysawit.databinding.FragmentLoginBinding
import com.example.mysawit.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.login(username, password)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginSuccessLD.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                binding.tvError.visibility = View.GONE
                findNavController().navigate(R.id.actionDashboardFragment)
            } else {
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }
}