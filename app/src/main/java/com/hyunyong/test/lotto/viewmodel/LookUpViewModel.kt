package com.hyunyong.test.lotto.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.hyunyong.test.lotto.data.Lotto
import com.hyunyong.test.lotto.db.LottoDao
import javax.inject.Inject

class LookUpViewModel @Inject constructor(val lottoDao: LottoDao) : ViewModel() {

    val progress = ObservableField<Boolean>()

    fun getLottoList() = run {
        MediatorLiveData<List<Lotto>>().apply {
            progress.set(true)
            addSource(lottoDao.getLottoList()) {
                value = it
                progress.set(false)
            }
        }
    }
}