package com.hyunyong.test.lotto.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyunyong.test.lotto.db.LottoDao
import java.util.stream.Collectors
import javax.inject.Inject

class LottoViewModel @Inject constructor(val lottoDao: LottoDao) : ViewModel() {

    val lottoNumber = ObservableField<List<Int>>()
    private val toastMessage = MutableLiveData<String>()

    fun getToastMessage(): LiveData<String> = toastMessage

    fun createLottoNumber() {
        val hashSet = HashSet<Int>()
        while (hashSet.size != 6) {
            val random = (Math.random() * 46).toInt()
            hashSet.add(random)
        }
        val numbers = hashSet.stream().sorted().collect(Collectors.toList())
        lottoNumber.set(numbers)
    }

    fun checkWinner(no: String?): LiveData<String> {
        val liveData = MutableLiveData<String>()
        if (no.isNullOrEmpty()) {
            toastMessage.value = "검색할 회차를 입력 해 주세요."
            return liveData
        }

        val drwNo = Integer.parseInt(no);
        if (lottoDao.countLotto() < drwNo) {
            toastMessage.value = "${lottoDao.countLotto()} 회차 아래로 입력하세요."
            return liveData
        }

        if (lottoNumber.get().isNullOrEmpty()) {
            toastMessage.value = "로또 번호를 생성 해 주세요."
            return liveData
        }

        val lotto = lottoDao.getLottoFromNo(drwNo)

        // test 용 주석 실제 로또 당첨
//        val list = ArrayList<Int>()
//        list.add(lotto.drwtNo1)
//        list.add(lotto.drwtNo2)
//        list.add(lotto.drwtNo3)
//        list.add(lotto.drwtNo4)
//        list.add(lotto.drwtNo5)
//        list.add(lotto.drwtNo6)
//        lottoNumber.set(list)
        // test end

        val number = lottoNumber.get()
        if(number.isNullOrEmpty()) {
            return liveData
        }


        var count = 0
        var isBunus = false
        number.forEach {
            if(it == lotto.drwtNo1) {
                count++
            } else if (it == lotto.drwtNo2) {
                count++
            } else if (it == lotto.drwtNo3) {
                count++
            } else if (it == lotto.drwtNo4) {
                count++
            } else if (it == lotto.drwtNo5) {
                count++
            } else if (it == lotto.drwtNo6) {
                count++
            } else if (it == lotto.bnusNo) {
                isBunus = true
            }
        }

        /**
         * 1등	6개숫자일치
         * 2등	5개숫자일치 +보너스일치
         * 3등	5개숫자일치
         * 4등	4개숫자일치
         * 5등	3개숫자일치 5,000원
         */

        if (count == 6) {
            // 1등
            liveData.value = "로또 1등 당첨을 축하 드립니다."

        } else if (count == 5) {
            if(isBunus) {
                // 2등
                liveData.value = "로또 2등 당첨을 축하 드립니다."
            } else {
                // 3등
                liveData.value = "로또 3등 당첨을 축하 드립니다."
            }
        } else if (count == 4) {
            // 4등
            liveData.value = "로또 4등 당첨을 축하 드립니다."
        } else if (count == 3) {
            // 5등
            liveData.value = "로또 5등 당첨을 축하 드립니다. \n5,000원 드릴께여"
        } else {
            liveData.value = "꽝!! 다음기회에..."
        }

        return liveData
    }
}