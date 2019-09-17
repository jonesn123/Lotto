package com.hyunyong.test.lotto.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hyunyong.test.lotto.db.LottoDao
import com.hyunyong.test.lotto.viewmodel.SplashViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val webservice: Webservice, private val lottoDao: LottoDao) {

    fun getLottoInformation(no: Int, countUpListener: SplashViewModel.CountUpListener): LiveData<List<Lotto>> {
        val liveData = MutableLiveData<List<Lotto>>()
        val data = ArrayList<Lotto>()
        countUpListener.clear()
        for (x in 1..no) {
            webservice.getInformationOfLotto(x).enqueue(object: Callback<Lotto> {
                override fun onFailure(call: Call<Lotto>, t: Throwable) {
                }

                override fun onResponse(call: Call<Lotto>, response: Response<Lotto>) {
                    val lotto = response.body()
                    if (lotto != null) {
                        data.add(lotto)
                        lottoDao.insert(lotto)
                        countUpListener.countUp()
                    }

                    if (x == no) {
                        liveData.value = data
                    }
                }
            })
        }

        return liveData
    }
}
