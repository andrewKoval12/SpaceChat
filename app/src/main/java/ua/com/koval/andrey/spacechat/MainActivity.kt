package ua.com.koval.andrey.spacechat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ua.com.koval.andrey.spacechat.databinding.ActivityMainBinding
import ua.com.koval.andrey.spacechat.models.users.Users
import ua.com.koval.andrey.spacechat.ui.activity.RegisterActivity
import ua.com.koval.andrey.spacechat.ui.fragments.ChatsFragment
import ua.com.koval.andrey.spacechat.ui.objects.AppDrawer
import ua.com.koval.andrey.spacechat.utilits.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
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
        if (AUTH.currentUser != null){
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
        initFirebase()
        initUser()
    }

    private fun initUser() {
        REF_DB_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER = it.getValue(Users::class.java) ?:Users()
            })
    }
}