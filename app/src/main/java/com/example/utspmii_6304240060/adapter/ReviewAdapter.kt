package com.example.utspmii_6304240060.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utspmii_6304240060.R
import com.example.utspmii_6304240060.databinding.ItemReviewBinding
import com.example.utspmii_6304240060.model.Review

class ReviewAdapter(
    private val reviewList: List<Review>
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    override fun getItemCount(): Int = reviewList.size

    class ReviewViewHolder(
        val binding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            val context = binding.root.context
            binding.tvReviewerName.text = review.reviewerName
            binding.tvReviewRating.text = context.getString(
                R.string.format_review_rating, review.rating
            )
            binding.tvReviewComment.text = review.comment
            binding.tvReviewDate.text = review.date
            binding.tvReviewerEmail.text = review.reviewerEmail
        }
    }
}
