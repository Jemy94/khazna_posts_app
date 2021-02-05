package com.jemy.khazna.ui.main.fragments.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jemy.khazna.R
import com.jemy.khazna.data.model.PostEntity
import kotlinx.android.synthetic.main.item_post.view.*

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var itemCallback: ((PostEntity?) -> Unit)? = null
    var items = mutableListOf<PostEntity>()

    fun addItems(items: List<PostEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view, itemCallback)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val popular = items[position]
        holder.bind(popular)
    }

    fun setItemCallBack(itemCallback: (PostEntity?) -> Unit) {
        this.itemCallback = itemCallback
    }

    override fun getItemCount(): Int = items.size
}

class PostViewHolder(
    itemView: View,
    private val itemCallback: ((PostEntity?) -> Unit)?
) :
    RecyclerView.ViewHolder(itemView) {

    private var title = itemView.postTitle

    fun bind(post: PostEntity?) {
        itemView.setOnClickListener { itemCallback?.invoke(post) }
        title.text = post?.title
    }
}