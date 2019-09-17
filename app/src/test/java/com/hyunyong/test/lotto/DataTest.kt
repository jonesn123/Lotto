package com.hyunyong.test.lotto

import com.hyunyong.test.lotto.data.Lotto
import com.hyunyong.test.lotto.data.OrderNumber
import org.junit.Assert
import org.junit.Test

class DataTest {
    @Test
    fun testLottoData() {
        val data = Lotto(
            1,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10.0,
            "test",
            "test",
            11.0,
            13,
            14.0
        )

        Assert.assertEquals("03 04 05 06 07 08", data.getLottoNumber())
        Assert.assertEquals("BonusNo: 09", data.getBonusString())
        Assert.assertEquals("01 회차", data.getNoString())
    }

    @Test
    fun testOrderData() {
        val orderNumber = OrderNumber(12, 3)

        Assert.assertEquals("12 회", orderNumber.getCountString())
        Assert.assertEquals("로또 번호 : 03", orderNumber.getNumberString())
    }
}
