package com.hyunyong.test.lotto.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.hyunyong.test.lotto.R
import com.hyunyong.test.lotto.databinding.FragmentLottoBinding
import com.hyunyong.test.lotto.viewmodel.LottoViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_lotto.*
import javax.inject.Inject

class LottoFragment : DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_lotto,
            container,
            false
        )
        return binding.root
    }

    lateinit var binding: FragmentLottoBinding
    lateinit var viewModel: LottoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LottoViewModel::class.java)
        binding.vm = viewModel

        viewModel.getToastMessage().observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

        btn_auto_create.setOnClickListener {
            viewModel.createLottoNumber()
        }

        btn_check_winner.setOnClickListener {
            viewModel.checkWinner(et_lotto_order.text.toString()).observe(this, Observer {
                AlertDialog.Builder(context).setTitle("당첨 결과").setMessage(it)
                    .setPositiveButton(
                        android.R.string.ok
                    ) { dialogInterface, _ -> dialogInterface.dismiss() }
                    .setCancelable(true).show()
            })
        }

        btn_check_previous.setOnClickListener {
            val action = object : NavDirections {
                override fun getActionId(): Int = R.id.lookup_action

                override fun getArguments(): Bundle {
                    return Bundle()
                }
            }
            findNavController().navigate(action)
        }

        btn_check_number_frequency.setOnClickListener {
            val action = object : NavDirections {
                override fun getActionId(): Int = R.id.frequency_action

                override fun getArguments(): Bundle {
                    return Bundle()
                }

            }
            findNavController().navigate(action)
        }
    }
}