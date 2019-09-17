package com.hyunyong.test.lotto.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.hyunyong.test.lotto.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WelcomeFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    @Inject
    lateinit var pref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.next_button).setOnClickListener {
            pref.edit().putBoolean("logIn", true).apply()
            val action = object: NavDirections {
                override fun getArguments(): Bundle {
                    val result = Bundle()
                    return result
                }

                override fun getActionId(): Int {
                    return R.id.lotto_action
                }

            }
            findNavController().navigate(action)
        }
    }
}
