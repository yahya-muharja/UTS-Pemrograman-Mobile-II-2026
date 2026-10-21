package com.example.utspmii_6304240060.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utspmii_6304240060.R
import com.example.utspmii_6304240060.adapter.ProductAdapter
import com.example.utspmii_6304240060.databinding.FragmentProductListBinding
import com.example.utspmii_6304240060.helpers.Config
import com.example.utspmii_6304240060.model.Product
import com.google.gson.Gson

class ProductListFragment : Fragment() {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        viewModel.loadProducts()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter { product -> openDetail(product) }

        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = productAdapter

        binding.rvProducts.itemAnimator = null
        binding.rvProducts.addItemDecoration(
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        )

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadProducts()
        }
    }

    private fun observeViewModel() {
        viewModel.products.observe(viewLifecycleOwner) { products ->
            binding.swipeRefresh.isRefreshing = false
            productAdapter.submitList(products)
            binding.tvProductCount.text =
                getString(R.string.info_product_count, products.size)
            binding.tvError.visibility = View.GONE
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (!isLoading) {
                binding.swipeRefresh.isRefreshing = false
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            binding.swipeRefresh.isRefreshing = false
            if (!message.isNullOrEmpty()) {
                binding.tvError.text = message
                binding.tvError.visibility = View.VISIBLE
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openDetail(product: Product) {
        val json = Gson().toJson(product)
        val intent = Intent(requireContext(), ProductDetailActivity::class.java)
        intent.putExtra(Config.EXTRA_PRODUCT_JSON, json)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvProducts.adapter = null
        _binding = null
    }
}
