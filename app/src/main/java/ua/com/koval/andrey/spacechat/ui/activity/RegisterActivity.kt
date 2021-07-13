package ua.com.koval.andrey.spacechat.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.ActivityRegisterBinding
import ua.com.koval.andrey.spacechat.ui.fragments.EnterPhoneNumberFragment
import ua.com.koval.andrey.spacechat.utilits.replaceFragment


class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment())
    }
}


