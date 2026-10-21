package com.example.utspmii_6304240060.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utspmii_6304240060.adapter.ReviewAdapter
import com.example.utspmii_6304240060.databinding.BottomSheetReviewsBinding
import com.example.utspmii_6304240060.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReviewBottomSheet : BottomSheetDialogFragment() {
    private var _binding: BottomSheetReviewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = arguments?.getString(ARG_REVIEWS_JSON).orEmpty()
        val type = object : TypeToken<List<Review>>() {}.type
        val reviews: List<Review> = if (json.isEmpty()) {
            emptyList()
        } else {
            Gson().fromJson(json, type)
        }

        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReviews.adapter = ReviewAdapter(reviews)

        binding.btnClose.setOnClickListener { dismiss() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ReviewBottomSheet"
        private const val ARG_REVIEWS_JSON = "arg_reviews_json"

        fun newInstance(reviews: List<Review>): ReviewBottomSheet {
            val sheet = ReviewBottomSheet()
            val args = Bundle()
            args.putString(ARG_REVIEWS_JSON, Gson().toJson(reviews))
            sheet.arguments = args
            return sheet
        }
    }
}
