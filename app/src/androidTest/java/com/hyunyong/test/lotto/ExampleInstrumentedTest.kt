package com.hyunyong.test.lotto

import android.content.Context
import androidx.core.content.edit
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.hyunyong.test.lotto.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityTestRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            val sharedPrefs = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
            sharedPrefs.edit { clear() }
            super.beforeActivityLaunched()
        }
    }

    @Test
    fun useAppContext() {
        // 스플리시 화면에서 로딩
        Thread.sleep(10000)

        onView(withId(R.id.next_button)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(1000)

        onView(withId(R.id.et_lotto_order)).check(matches(isDisplayed())).perform(replaceText("1"))
        onView(withId(R.id.btn_auto_create)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.btn_check_winner)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(1000)
        onView(withText("확인")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.btn_check_previous)).check(matches(isDisplayed())).perform(click())
        Thread.sleep(1000)

        Espresso.pressBack()

        onView(withId(R.id.btn_check_number_frequency)).check(matches(isDisplayed()))
            .perform(click())
        Thread.sleep(1000)
    }
}
