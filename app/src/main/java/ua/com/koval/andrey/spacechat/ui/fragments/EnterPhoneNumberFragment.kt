package ua.com.koval.andrey.spacechat.ui.fragments


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import ua.com.koval.andrey.spacechat.MainActivity
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentEnterPhoneNumberBinding
import ua.com.koval.andrey.spacechat.ui.activity.RegisterActivity
import ua.com.koval.andrey.spacechat.utilits.replaceActivity
import ua.com.koval.andrey.spacechat.utilits.replaceFragment
import ua.com.koval.andrey.spacechat.utilits.showToast
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {
    private var _binding: FragmentEnterPhoneNumberBinding? = null
    private val binding get() = _binding!!
    private lateinit var mPhoneNumber: String
    private lateinit var auth: FirebaseAuth
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterPhoneNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                auth.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast("Welcome!")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else {
                        showToast(it.exception?.message.toString())
                    }
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    showToast(e.message.toString())
                } else if (e is FirebaseTooManyRequestsException) {
                    showToast(e.message.toString())
                }


            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                replaceFragment(EnterCodeFragment(mPhoneNumber, verificationId))
            }
        }
        binding.registerBtnNext.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.registerInputPhoneNumber.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))
        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = binding.registerInputPhoneNumber.text.toString().trim()
        val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
            .setPhoneNumber(mPhoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity as RegisterActivity)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}