package com.espressotestcases

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.espressotestcases.activity.HomeActivity
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4ClassRunner::class)
class HomeTest {


    @get:Rule
    var activityRule: ActivityTestRule<HomeActivity> = ActivityTestRule(
        HomeActivity::class.java)


    @Test
    fun clickOnSpinnerItem() {
        onView(withId(R.id.colorSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.colorSpinner)).check(matches(withSpinnerText(containsString("Black"))));
    }

    @Test
    fun clickOnRecyclerView() {

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
        )

    }

    @Test
    fun recyclerViewScroll() {

        val recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.recyclerView)
        var itemCount: Int = recyclerView.adapter!!.itemCount

        onView(withId(R.id.recyclerView)).inRoot(
            RootMatchers.withDecorView(
                Matchers.`is`
                    (activityRule.activity.window.decorView)
            )
        ).perform(scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))


    }


}



