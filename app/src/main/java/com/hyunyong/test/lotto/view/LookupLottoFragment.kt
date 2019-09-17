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
import com.hyunyong.test.lotto.databinding.FragmentLookupBinding
import com.hyunyong.test.lotto.viewmodel.LookUpViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_lookup.*
import javax.inject.Inject

class LookupLottoFragment : DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_lookup,
            container,
            false
        )
        return binding.root
    }

    lateinit var binding: FragmentLookupBinding
    lateinit var viewmodel: LookUpViewModel
    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProviders.of(this, viewmodelFactory).get(LookUpViewModel::class.java)
        binding.vm = viewmodel

        val adapter = LookupRecyclerAdapter()
        rv_lookup.adapter = adapter

        viewmodel.getLottoList().observe(this, Observer {
            adapter.addLottoList(it)
        })
    }
}