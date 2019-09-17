package com.hyunyong.test.lotto.data

data class OrderNumber(val count: Int, val number: Int) {

    fun getCountString() = "$count 회"
    fun getNumberString() = "로또 번호 : ${String.format("%02d",number)}"
}