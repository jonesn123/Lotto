package com.hyunyong.test.lotto.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lotto")
data class Lotto(
    @PrimaryKey val drwNo: Int,
    val drwtNo1: Int,
    val drwtNo2: Int,
    val drwtNo3: Int,
    val drwtNo4: Int,
    val drwtNo5: Int,
    val drwtNo6: Int,
    val bnusNo: Int,
    val totSellamnt: Double,
    val returnValue: String,
    val drwNoDate: String,
    val firstWinamnt: Double,
    val firstPrzwnerCo: Int,
    val firstAccumamnt: Double
) {
    fun getLottoNumber(): String =
        "${String.format("%02d", drwtNo1)} ${String.format("%02d", drwtNo2)} ${String.format(
            "%02d",
            drwtNo3
        )} ${String.format("%02d", drwtNo4)} ${String.format(
            "%02d",
            drwtNo5
        )} ${String.format("%02d", drwtNo6)}"

    fun getBonusString(): String = "BonusNo: ${String.format("%02d", bnusNo)}"

    fun getNoString(): String = "${String.format("%02d", drwNo)} 회차"
}