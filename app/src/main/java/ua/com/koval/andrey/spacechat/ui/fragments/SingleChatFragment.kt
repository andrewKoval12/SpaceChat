package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.ActivityMainBinding
import ua.com.koval.andrey.spacechat.models.users.CommonModel

class SingleChatFragment(contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

}