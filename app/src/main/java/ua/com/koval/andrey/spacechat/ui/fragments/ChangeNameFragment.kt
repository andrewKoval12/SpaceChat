package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.*
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentChangeNameBinding
import ua.com.koval.andrey.spacechat.utilits.*


class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    private var _binding: FragmentChangeNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initFullName()
    }

    private fun initFullName() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1){
            binding.settingsInputName.setText(fullnameList[0])
            binding.settingsInputSurname.setText(fullnameList[1])
        }else binding.settingsInputName.setText(fullnameList[0])
    }


    override fun change() {
        val name = binding.settingsInputName.text.toString().trim()
        val surname = binding.settingsInputSurname.text.toString().trim()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullName = "$name $surname"
            REF_DB_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME)
                .setValue(fullName).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.toast_data_update))
                        USER.fullname = fullName
                        parentFragmentManager.popBackStack()
                    }
                }
        }
    }

}