package ua.com.koval.andrey.spacechat.ui.fragments

import androidx.fragment.app.Fragment
import ua.com.koval.andrey.spacechat.MainActivity


open class BaseFragment( layout:Int) : Fragment(layout) {

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()
    }
}