package com.hyunyong.test.lotto.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyunyong.test.lotto.data.Lotto
import com.hyunyong.test.lotto.data.OrderNumber
import com.hyunyong.test.lotto.db.LottoDao
import javax.inject.Inject

class FrequencyViewModel @Inject constructor(val lottoDao: LottoDao) : ViewModel() {

    private val orderNumber = MutableLiveData<List<OrderNumber>>()
    fun getOrderNumber(): LiveData<List<OrderNumber>> = orderNumber

    val progress = ObservableField<Boolean>()
    fun getFrequencyLottoNumber() = run {
        MediatorLiveData<List<Lotto>>().apply {
            progress.set(true)
            addSource(lottoDao.getLottoList()) {

                val map = HashMap<Int, Int>()
                it.forEach { lotto ->
                    if (!map.containsKey(lotto.drwtNo1)) {
                        map[lotto.drwtNo1] = 0
                    } else {
                        map[lotto.drwtNo1] = map[lotto.drwtNo1]!!.plus(1)
                    }
                    if (!map.containsKey(lotto.drwtNo2)) {
                        map[lotto.drwtNo2] = 0
                    } else {
                        map[lotto.drwtNo2] = map[lotto.drwtNo2]!!.plus(1)
                    }
                    if (!map.containsKey(lotto.drwtNo3)) {
                        map[lotto.drwtNo3] = 0
                    } else {
                        map[lotto.drwtNo3] = map[lotto.drwtNo3]!!.plus(1)
                    }
                    if (!map.containsKey(lotto.drwtNo4)) {
                        map[lotto.drwtNo4] = 0
                    } else {
                        map[lotto.drwtNo4] = map[lotto.drwtNo4]!!.plus(1)
                    }
                    if (!map.containsKey(lotto.drwtNo5)) {
                        map[lotto.drwtNo5] = 0
                    } else {
                        map[lotto.drwtNo5] = map[lotto.drwtNo5]!!.plus(1)
                    }
                    if (!map.containsKey(lotto.drwtNo6)) {
                        map[lotto.drwtNo6] = 0
                    } else {
                        map[lotto.drwtNo6] = map[lotto.drwtNo6]!!.plus(1)
                    }
                    if (!map.containsKey(lotto.bnusNo)) {
                        map[lotto.bnusNo] = 0
                    } else {
                        map[lotto.bnusNo] = map[lotto.bnusNo]!!.plus(1)
                    }
                }

                val result = ArrayList<OrderNumber>()
                map.forEach { (number, count) ->
                    result.add(OrderNumber(count, number))
                }
                result.sortWith(object: Comparator<OrderNumber>{
                    override fun compare(p0: OrderNumber?, p1: OrderNumber?): Int {
                        p0 ?: return -1
                        p1 ?: return -1
                        if (p0.count < p1.count) {
                            return 1
                        }
                        return -1
                    }

                })
                orderNumber.value = result

                progress.set(false)
            }
        }
    }

}