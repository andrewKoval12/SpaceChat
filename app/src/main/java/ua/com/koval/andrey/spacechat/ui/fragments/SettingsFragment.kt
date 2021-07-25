package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.*
import ua.com.koval.andrey.spacechat.MainActivity
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentSettingsBinding
import ua.com.koval.andrey.spacechat.ui.activity.RegisterActivity
import ua.com.koval.andrey.spacechat.utilits.AUTH
import ua.com.koval.andrey.spacechat.utilits.USER
import ua.com.koval.andrey.spacechat.utilits.replaceActivity
import ua.com.koval.andrey.spacechat.utilits.replaceFragment

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        with(binding) {
            settingsBio.text = USER.bio
            settingsLogin.text = USER.fullname
            settingsPhoneNumber.text = USER.phone
            settingsStatus.text = USER.status
            settingsUsername.text = USER.username
            settingsBtnChangeLogin.setOnClickListener { replaceFragment(ChangeUserNameFragment())}
            settingsBtnChangeBio.setOnClickListener { replaceFragment(ChangeBioFragment()) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment())
            }
        }
        return true
    }
}