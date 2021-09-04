package ua.com.koval.andrey.spacechat.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import de.hdodenhof.circleimageview.CircleImageView
import ua.com.koval.andrey.spacechat.R
import ua.com.koval.andrey.spacechat.databinding.FragmentContactItemBinding
import ua.com.koval.andrey.spacechat.databinding.FragmentContactsBinding
import ua.com.koval.andrey.spacechat.models.users.CommonModel
import ua.com.koval.andrey.spacechat.utilits.*


class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FirebaseRecyclerAdapter<CommonModel, ContactsHolder>
    private lateinit var mRefContacts: DatabaseReference
    private lateinit var mRefUsers: DatabaseReference
    private lateinit var mRefUserListener: AppValueEventListener
    private val mapListener = hashMapOf<DatabaseReference, AppValueEventListener>()

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Contacts"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = binding.contactsRecycleView
        mRefContacts = REF_DB_ROOT.child(NODE_PHONES_CONTACTS).child(CURRENT_UID)

        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(mRefContacts, CommonModel::class.java)
            .build()

        mAdapter = object : FirebaseRecyclerAdapter<CommonModel, ContactsHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
                val view = FragmentContactItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ContactsHolder(view)
            }

            override fun onBindViewHolder(
                holder: ContactsHolder,
                position: Int,
                model: CommonModel
            ) {
                mRefUsers = REF_DB_ROOT.child(NODE_USERS).child(model.id)

                mRefUserListener = AppValueEventListener {
                    val contact =  it.getCommonModel()
                    holder.name.text = contact.fullname
                    holder.status.text = contact.state
                    holder.photo.downloadAndSetImage(contact.photoUrl)
                }
                mRefUsers.addValueEventListener(mRefUserListener)
                mapListener[mRefUsers] = mRefUserListener
            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    class ContactsHolder(binding: FragmentContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.contactFullname
        val status: TextView = binding.contactStatus
        val photo: CircleImageView = binding.contactPhoto

    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
        mapListener.forEach{
            it.key.removeEventListener(it.value)
        }
    }
}