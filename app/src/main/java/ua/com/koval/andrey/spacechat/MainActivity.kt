package ua.com.koval.andrey.spacechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import ua.com.koval.andrey.spacechat.databinding.ActivityMainBinding
import ua.com.koval.andrey.spacechat.ui.activity.RegisterActivity
import ua.com.koval.andrey.spacechat.ui.fragments.ChatsFragment
import ua.com.koval.andrey.spacechat.ui.objects.AppDrawer
import ua.com.koval.andrey.spacechat.utilits.replaceActivity
import ua.com.koval.andrey.spacechat.utilits.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (false){
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment())
        } else {
            replaceActivity(RegisterActivity())
        }
    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this,mToolbar)
    }
}