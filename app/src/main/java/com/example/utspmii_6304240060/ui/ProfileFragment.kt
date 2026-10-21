package com.example.utspmii_6304240060.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.utspmii_6304240060.R
import com.example.utspmii_6304240060.databinding.FragmentProfileBinding
import com.example.utspmii_6304240060.helpers.Config

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvName.text = Config.DEVELOPER_NAME
        binding.tvNim.text = getString(R.string.format_nim, Config.DEVELOPER_NIM)
        binding.tvClass.text = Config.DEVELOPER_CLASS
        binding.tvEmail.text = Config.DEVELOPER_EMAIL
        binding.tvPhone.text = Config.DEVELOPER_PHONE
        binding.tvProdi.text = Config.DEVELOPER_PRODI
        binding.tvJurusan.text = Config.DEVELOPER_JURUSAN
        binding.tvUniversity.text = Config.DEVELOPER_UNIVERSITY
        binding.tvAddress.text = Config.DEVELOPER_ADDRESS

        Glide.with(binding.imgProfile)
            .load(R.drawable.foto_ym)
            .placeholder(R.drawable.ic_person_placeholder)
            .error(R.drawable.ic_person_placeholder)
            .circleCrop()
            .into(binding.imgProfile)

        Glide.with(binding.imgLogo)
            .load(R.drawable.polbeng_logo)
            .placeholder(R.drawable.ic_person_placeholder)
            .into(binding.imgLogo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
