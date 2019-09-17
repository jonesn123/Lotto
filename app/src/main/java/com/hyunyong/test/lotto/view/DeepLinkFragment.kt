package com.hyunyong.test.lotto.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hyunyong.test.lotto.R
import com.hyunyong.test.lotto.databinding.FragmentDeeplinkBinding
import com.hyunyong.test.lotto.viewmodel.DeepLinkViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DeepLinkFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_deeplink,
            container,
            false
        )
        return binding.root
    }

    lateinit var binding: FragmentDeeplinkBinding
    lateinit var viewModel: DeepLinkViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DeepLinkViewModel::class.java)

        val no = arguments?.getInt("no") ?: 1

        viewModel.getLottoDao(no).observe(this, Observer {
            binding.lotto = it
        })
    }
}