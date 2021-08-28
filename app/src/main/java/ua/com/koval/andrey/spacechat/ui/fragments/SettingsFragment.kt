package ua.com.koval.andrey.spacechat.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentSettingsBinding
import ua.com.koval.andrey.spacechat.ui.activity.RegisterActivity
import ua.com.koval.andrey.spacechat.utilits.*

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
            settingsChangePhoto.setOnClickListener { changePhotoUser() }
        }
    }

    private fun changePhotoUser() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(APP_ACTIVITY)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (APP_ACTIVITY).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> {
                replaceFragment(ChangeNameFragment())
            }
        }
        return true
    }
}