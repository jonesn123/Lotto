package com.hyunyong.test.lotto.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {

    @GET("common.do?method=getLottoNumber")
    fun getInformationOfLotto(@Query("drwNo") no: Int): Call<Lotto>

}
