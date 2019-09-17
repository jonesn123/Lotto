package com.hyunyong.test.lotto.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hyunyong.test.lotto.data.Lotto

@Dao
interface LottoDao {

    @Query("SELECT * FROM lotto")
    fun getLottoList(): LiveData<List<Lotto>>

    @Query("SELECT * from lotto where drwNo == :no;")
    fun getLottoFromNo(no: Int): Lotto

    @Query("SELECT * from lotto where drwNo == :no;")
    fun getLottoLiveDataFromNo(no: Int): LiveData<Lotto>

    @Query("SELECT COUNT(*) FROM lotto")
    fun countLotto(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lotto: Lotto)

    @Query("DELETE FROM lotto")
    fun deleteAll()
}