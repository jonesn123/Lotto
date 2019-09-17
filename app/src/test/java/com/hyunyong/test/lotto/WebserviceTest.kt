package com.hyunyong.test.lotto

import com.hyunyong.test.lotto.data.Webservice
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertEquals

class WebserviceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var webservice: Webservice

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        webservice = retrofit.create(Webservice::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getInformationOfLotto() {
        mockWebServer.enqueue(MockResponse().setBody(
            """
                {"totSellamnt":76994062000,"returnValue":"success","drwNoDate":"2018-03-31","firstWinamnt":1632246205,"drwtNo6":45,"drwtNo4":12,"firstPrzwnerCo":11,"drwtNo5":28,"bnusNo":26,"firstAccumamnt":17954708255,"drwNo":800,"drwtNo2":4,"drwtNo3":10,"drwtNo1":1}
            """.trimIndent()
        ))

        val call = webservice.getInformationOfLotto(800)
        val response = call.execute()

        val entity = response.body()!!
        assertEquals(800, entity.drwNo)

        val request = mockWebServer.takeRequest()
        assertEquals("GET", request.method)
        assertEquals("/common.do?method=getLottoNumber&drwNo=800", request.path)
    }
}