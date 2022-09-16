package com.example.vpdmoney.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vpdmoney.R
import com.example.vpdmoney.data.remote.dto.UsersDtoItem
import com.example.vpdmoney.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user), UsersAdapter.OnItemClickListener {

    val viewModel: UsersViewModel by viewModels()

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

     var userAdapter = UsersAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserBinding.bind(view)

        setUpRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when(viewState) {
                        is UsersViewState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            userAdapter.submitList(viewState.userList)
                        }
                        is UsersViewState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is UsersViewState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                viewState.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onItemClick(user: UsersDtoItem) {
        val action = UserFragmentDirections.actionUserFragmentToDetailFragment(user)
        findNavController().navigate(action)

    }

    private fun setUpRecyclerView() {
        binding.usersRecyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

