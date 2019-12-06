package com.espressotestcases

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.espressotestcases.activity.ContactActivity
import com.espressotestcases.activity.UserDetailActivity
import org.hamcrest.core.IsNot.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class UserDetailTest {

    private val VALID_PHONE_NUMBER = "123-345-67890"

    @get:Rule
    var intentsTestRule = IntentsTestRule(UserDetailActivity::class.java)


    @Before
    fun stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(
            Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        )
    }


    @Test
    fun pickContactNumber() {
        intending(hasComponent(hasShortClassName(".ContactActivity"))).respondWith(
            Instrumentation.ActivityResult(
                Activity.RESULT_OK,
                ContactActivity.createResultData(VALID_PHONE_NUMBER)
            )
        )
        onView(withId(R.id.button_pick_contact)).perform(click())
        onView(withId(R.id.edit_text_caller_number)).check(matches(withText(VALID_PHONE_NUMBER)))


    }


}