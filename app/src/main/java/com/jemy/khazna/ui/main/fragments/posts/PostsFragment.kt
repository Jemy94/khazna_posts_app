package com.jemy.khazna.ui.main.fragments.posts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.jemy.khazna.R
import com.jemy.khazna.data.model.PostEntity
import com.jemy.khazna.ui.main.MainActivity
import com.jemy.khazna.ui.main.fragments.posts.adapter.PostsAdapter
import com.jemy.khazna.utils.Constants
import com.jemy.khazna.utils.ResourceState
import com.jemy.khazna.utils.extensions.messageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment : Fragment(R.layout.fragment_posts) {


    @Inject
    lateinit var viewModelFactory: PostsViewModelFactory
    private val viewModel: PostsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(PostsViewModel::class.java)
    }
    private val postsAdapter by lazy { PostsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAllPosts()
        setupToolbarTitle()
        setSwipeAction()
    }

    private fun setupToolbarTitle() {
        toolbarTitle.text = getString(R.string.posts)
        (activity as MainActivity).setSupportActionBar(toolbar)
    }

    private fun setSwipeAction() {
        swipeToRefresh.setOnRefreshListener {
            postsAdapter.clear()
            getAllPosts()
        }
    }

    private fun getAllPosts() {
        viewModel.getPosts().observe(viewLifecycleOwner, { resources ->
            when (resources.state) {
                ResourceState.LOADING -> {
                    swipeToRefresh.isRefreshing = true
                }
                ResourceState.SUCCESS -> {
                    swipeToRefresh.isRefreshing = false
                    resources.data?.let { posts ->
                        if (posts.isEmpty()) {
                            messageDialog(R.string.there_are_no_posts)?.show()
                        } else {
                            setupPostsAdapter(posts)
                            setupPostClickListener()
                            Log.d("PostsFragment", posts.size.toString())
                        }
                    }
                }
                ResourceState.ERROR -> {
                    swipeToRefresh.isRefreshing = false
                    resources.message?.let { messageDialog(message = it)?.show() }
                }
            }
        })
    }

    private fun setupPostsAdapter(list: List<PostEntity>) {
        postsRecycler.adapter = postsAdapter
        postsAdapter.addItems(list)
    }

    private fun setupPostClickListener() {
        postsAdapter.setItemCallBack { post ->
            val bundle = bundleOf(Constants.Key.POST to post)
            view?.findNavController()
                ?.navigate(R.id.action_postsFragment_to_postDetailsFragment, bundle)
        }
    }
}