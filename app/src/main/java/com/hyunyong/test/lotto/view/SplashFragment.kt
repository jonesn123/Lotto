package com.hyunyong.test.lotto.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.hyunyong.test.lotto.R
import com.hyunyong.test.lotto.databinding.FragmentSplashBinding
import com.hyunyong.test.lotto.viewmodel.SplashViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_splash,
            container,
            false
        )
        return binding.root
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var pref: SharedPreferences

    lateinit var viewModel: SplashViewModel
    lateinit var binding: FragmentSplashBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        binding.vm = viewModel

        // 800차까지 로또 정보를 조회한다.
        viewModel.getLottoInformation(800).observe(this, Observer {

            val action = object : NavDirections {
                override fun getArguments(): Bundle {
                    val deeplinkIntent = activity?.intent
                    val result = Bundle()
                    deeplinkIntent?.data?.let {
                        if (it.scheme == "link.lotto") {
                            result.putInt("no", Integer.parseInt(it.schemeSpecificPart))
                        }
                    }
                    return result
                }

                override fun getActionId(): Int {
                    val deeplinkIntent = activity?.intent
                    deeplinkIntent?.data?.scheme?.let {
                        if (it == "link.lotto") {
                            return R.id.deeplink_acition
                        }
                    }

                    return if(pref.getBoolean("logIn", false)) {
                        R.id.lotto_action
                    } else {
                        R.id.welcome_action
                    }
                }
            }
            findNavController().navigate(action)
        })
    }
}