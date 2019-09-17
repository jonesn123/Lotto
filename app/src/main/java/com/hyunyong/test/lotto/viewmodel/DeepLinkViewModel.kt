package com.hyunyong.test.lotto.viewmodel

import androidx.lifecycle.ViewModel
import com.hyunyong.test.lotto.db.LottoDao
import javax.inject.Inject

class DeepLinkViewModel @Inject constructor(val lottoDao: LottoDao) : ViewModel() {

    fun getLottoDao(no: Int) = lottoDao.getLottoLiveDataFromNo(no)
}