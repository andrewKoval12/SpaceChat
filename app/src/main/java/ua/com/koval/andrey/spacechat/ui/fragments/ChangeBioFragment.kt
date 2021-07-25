package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentChangeBioBinding
import ua.com.koval.andrey.spacechat.utilits.*


class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    private var _binding: FragmentChangeBioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeBioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.settingsInputBio.setText(USER.bio)

    }

    override fun change() {
        super.change()
        val newBio = binding.settingsInputBio.text.toString()
        REF_DB_ROOT.child(NODE_USERS).child(UID).child(CHILD_BIO).setValue(newBio)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    USER.bio = newBio
                    fragmentManager?.popBackStack()
                }
            }
    }
}