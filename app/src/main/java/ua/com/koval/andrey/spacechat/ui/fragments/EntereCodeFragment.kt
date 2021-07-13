package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentEnterCodeBinding
import ua.com.koval.andrey.spacechat.utilits.AppTextWatcher
import ua.com.koval.andrey.spacechat.utilits.showToast


class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {
    private var _binding: FragmentEnterCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterCodeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        binding.registerInputCode.addTextChangedListener(AppTextWatcher {
            val string = binding.registerInputCode.text.toString()
            if (string.length == 6) {
                verifiCode()
            }
        })

    }

    fun verifiCode() {
        showToast("OK")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}