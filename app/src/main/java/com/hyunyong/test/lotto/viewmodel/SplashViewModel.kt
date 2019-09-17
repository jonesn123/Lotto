package com.hyunyong.test.lotto.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.hyunyong.test.lotto.data.Lotto
import com.hyunyong.test.lotto.data.Repository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val progress = ObservableField(false)
    var countNum = ObservableField(0)

    fun getLottoInformation(count: Int) =
        MediatorLiveData<List<Lotto>>().apply {
            val liveData = repository.getLottoInformation(count,
                object : CountUpListener {
                    override fun clear() {
                        countNum.set(0)
                    }

                    override fun countUp() {
                        countNum.set(countNum.get()?.plus(1))
                    }
                })
            progress.set(true)
            addSource(liveData) {
                value = it
                progress.set(false)
            }
        }

    interface CountUpListener {
        fun countUp()
        fun clear()
    }
}