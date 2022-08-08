package com.android.themoviedbapp.fragment.movie_review

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.entity.movie_reviews.Review
import com.android.themoviedbapp.databinding.MovieReviewItemLayoutBinding
import com.bumptech.glide.Glide

class MovieReviewAdapter : PagingDataAdapter<Review, MovieReviewItemViewHolder>(differ) {
    override fun onBindViewHolder(holder: MovieReviewItemViewHolder, position: Int) {

        holder.binding.data = getItem(position)
        holder.binding.cardViewContent.animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            com.android.themoviedbapp.R.anim.animation
        )
        getItem(position)?.authorDetails?.avatarPath?.let {
            if (it.substring(0, 5).equals("/http")) {
                Glide.with(holder.binding.root).load(it.substring(1))
                    .into(holder.binding.imgAvatar)
            } else {
                Glide.with(holder.binding.root)
                    .load("https://www.themoviedb.org/t/p/w300_and_h300_face${it}")
                    .circleCrop().into(holder.binding.imgAvatar)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieReviewItemViewHolder {
        return MovieReviewItemViewHolder(
            MovieReviewItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return false
            }
        }
    }
}

class MovieReviewItemViewHolder(val binding: MovieReviewItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)