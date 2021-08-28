package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.*
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentChangeUserNameBinding
import ua.com.koval.andrey.spacechat.utilits.*
import java.util.*


class ChangeUserNameFragment : BaseChangeFragment(R.layout.fragment_change_user_name) {

    private var _binding: FragmentChangeUserNameBinding? = null
    private val binding get() = _binding!!
    lateinit var mNewUsername: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeUserNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            settingsInputUsername.setText(USER.username)
        }
    }



    override fun change() {
        mNewUsername = binding.settingsInputUsername.text.toString().lowercase(Locale.getDefault())
        if (mNewUsername.isEmpty()) {
            showToast("Empty field!")
        } else {
            REF_DB_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("This user has already been created")
                    } else {
                        changeUserName()
                    }
                })
        }
    }

    private fun changeUserName() {
        REF_DB_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    upDateCurrentUserName()
                }
            }
    }

    private fun upDateCurrentUserName() {
        REF_DB_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME)
            .setValue(mNewUsername)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.toast_data_update))
                    deleteOldUserName()
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
    }

    private fun deleteOldUserName() {
        REF_DB_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    showToast(getString(R.string.toast_data_update))
                    fragmentManager?.popBackStack()
                    USER.username = mNewUsername
                } else {
                    showToast(it.exception?.message.toString())
                }
            }
            }
    }
