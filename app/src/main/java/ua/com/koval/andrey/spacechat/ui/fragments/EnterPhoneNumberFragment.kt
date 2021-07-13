package ua.com.koval.andrey.spacechat.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentEnterPhoneNumberBinding
import ua.com.koval.andrey.spacechat.utilits.replaceFragment
import ua.com.koval.andrey.spacechat.utilits.showToast


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {
    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.registerInputPhoneNumber.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.registerInputPhoneNumber.text.toString().isEmpty()){
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            replaceFragment(EnterCodeFragment())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}