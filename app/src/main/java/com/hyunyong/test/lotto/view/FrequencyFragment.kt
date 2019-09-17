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
import com.hyunyong.test.lotto.databinding.FragmentFrequencyBinding
import com.hyunyong.test.lotto.viewmodel.FrequencyViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_frequency.*
import kotlinx.android.synthetic.main.fragment_lookup.*
import javax.inject.Inject

class FrequencyFragment : DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_frequency,
            container,
            false
        )
        return binding.root
    }

    lateinit var binding: FragmentFrequencyBinding
    lateinit var viewmodel: FrequencyViewModel
    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel =
            ViewModelProviders.of(this, viewmodelFactory).get(FrequencyViewModel::class.java)
        binding.vm = viewmodel

        val adapter = FrequencyRecyclerAdapter()
        rv_ordering.adapter = adapter

        viewmodel.getOrderNumber().observe(this, Observer {
            adapter.addNumbers(it)
        })

        viewmodel.getFrequencyLottoNumber().observe(this, Observer {
            //nothing
        })
    }
}