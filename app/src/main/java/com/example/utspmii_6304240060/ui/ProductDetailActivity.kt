package com.example.utspmii_6304240060.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.utspmii_6304240060.R
import com.example.utspmii_6304240060.databinding.ActivityProductDetailBinding
import com.example.utspmii_6304240060.helpers.Config
import com.example.utspmii_6304240060.model.Product
import com.google.gson.Gson

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applyWindowInsets()
        setupToolbar()

        val json = intent.getStringExtra(Config.EXTRA_PRODUCT_JSON)
        if (json.isNullOrEmpty()) {
            finish()
            return
        }

        product = Gson().fromJson(json, Product::class.java)
        bindProduct(product!!)
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.appBarLayout.updatePadding(top = systemBars.top)
            view.updatePadding(left = systemBars.left, right = systemBars.right)
            insets
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_product_detail)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun bindProduct(item: Product) {
        Glide.with(binding.imgProduct)
            .load(item.mainImage)
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_image_placeholder)
            .into(binding.imgProduct)

        binding.tvTitle.text = item.title
        binding.tvProductId.text = getString(R.string.format_product_id, item.id)
        binding.tvCategory.text = item.category
        binding.tvDescription.text = item.description
        binding.tvPrice.text = getString(R.string.format_price, item.price)
        binding.tvStock.text = getString(R.string.format_stock, item.stock)
        binding.tvRating.text = getString(R.string.format_rating, item.rating)
        binding.tvAvailability.text = item.availabilityStatus
        binding.tvTags.text = item.tags.joinToString(", ")
        binding.tvDimensions.text = getString(
            R.string.format_dimensions,
            item.dimensions.width,
            item.dimensions.height,
            item.dimensions.depth
        )

        val topReview = item.reviews.firstOrNull()
        if (topReview != null) {
            binding.reviewContainer.visibility = View.VISIBLE
            binding.tvTopReviewRating.text =
                getString(R.string.format_review_rating, topReview.rating)
            binding.tvTopReviewComment.text = topReview.comment
            binding.tvTopReviewer.text = getString(
                R.string.format_reviewer, topReview.reviewerName, topReview.date
            )
        } else {
            binding.reviewContainer.visibility = View.GONE
        }

        if (item.reviews.size > 1) {
            binding.btnSeeAllReviews.visibility = View.VISIBLE
            binding.btnSeeAllReviews.text =
                getString(R.string.action_see_all_reviews, item.reviews.size)
            binding.btnSeeAllReviews.setOnClickListener {
                ReviewBottomSheet.newInstance(item.reviews)
                    .show(supportFragmentManager, ReviewBottomSheet.TAG)
            }
        } else {
            binding.btnSeeAllReviews.visibility = View.GONE
        }
    }
}
