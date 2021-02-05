package com.jemy.khazna.ui.main.fragments.postdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jemy.khazna.R
import com.jemy.khazna.data.model.PostEntity
import com.jemy.khazna.ui.main.MainActivity
import com.jemy.khazna.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_post_details.*

@AndroidEntryPoint
class PostDetailsFragment : Fragment(R.layout.fragment_post_details) {


    private val post: PostEntity? by lazy { arguments?.getParcelable(Constants.Key.POST) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarTitle()
        getPostDetails()
    }

    private fun setupToolbarTitle() {
        toolbarTitle.text = getString(R.string.post_details)
        (activity as MainActivity).setSupportActionBar(toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getPostDetails() {
        if (post != null) {
            title.text = post!!.title
            body.text = post!!.body
        }
    }
}