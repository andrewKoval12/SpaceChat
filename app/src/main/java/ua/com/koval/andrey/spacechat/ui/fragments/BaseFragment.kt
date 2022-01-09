package ua.com.koval.andrey.spacechat.ui.fragments

import androidx.fragment.app.Fragment
import ua.com.koval.andrey.spacechat.utilits.APP_ACTIVITY


open class BaseFragment( layout:Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        APP_ACTIVITY.mAppDrawer.enableDrawer()
    }
}