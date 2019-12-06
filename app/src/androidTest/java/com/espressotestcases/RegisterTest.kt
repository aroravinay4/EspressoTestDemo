package com.espressotestcases

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.EditText
import androidx.annotation.StringRes

import android.content.Context
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import android.view.View
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.base.DefaultFailureHandler
import androidx.test.espresso.FailureHandler
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.espressotestcases.activity.RegisterActivity
import org.junit.Before
import org.junit.rules.TestName


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class RegisterTest {


    @get:Rule
    var activityRule: ActivityTestRule<RegisterActivity> =
        ActivityTestRule(RegisterActivity::class.java)

    @Test
    fun firstNameIsEmpty() {
        onView(withId(R.id.firstNameEditText)).perform(clearText())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.firstNameEditText)).check(matches(setError(getString(R.string.first_name_required))))

        /*      onView(withId(R.id.firstNameEditText)).check(matches(hasErrorText("First name required")))
                  .check(matches(withHintColor(Color.WHITE))
                  )*/
    }


/*
    @get:Rule
    val mRuntimePermissionRule: GrantPermissionRule = GrantPermissionRule
        .grant(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )
*/


    @get:Rule
    val testName: TestName = TestName()

    @Test
    fun lastNameIsEmpty() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(clearText())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.lastNameEditText)).check(matches(setError(getString(R.string.last_name_required))))
    }


    @Test
    fun emailIsEmpty() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(clearText())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.emailEditText)).check(matches(setError(getString(R.string.email_required))))
    }

    @Test
    fun emailIsInvalid() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(typeText("invalid"), closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.emailEditText)).check(matches(setError(getString(R.string.invalid_email))))
    }


    @Test
    fun passwordIsEmpty() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(typeText("demo@demo.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordNameEditText)).perform(clearText())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.passwordNameEditText)).check(matches(setError(getString(R.string.password_required))))
    }

    @Test
    fun passwordIsShort() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(
            typeText("demo@demo.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordNameEditText)).perform(typeText("1234"), closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.passwordNameEditText)).check(matches(setError(getString(R.string.invalid_password))))
    }


    @Test
    fun confirmPasswordIsEmpty() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(
            typeText("demo@demo.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordNameEditText)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordNameEditText)).perform(clearText())
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.confirmPasswordNameEditText)).check(matches(setError(getString(R.string.confirm_password_required))))
    }

    @Test
    fun confirmPasswordIsShort() {

        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(
            typeText("demo@demo.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordNameEditText)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordNameEditText)).perform(
            typeText("1234"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.confirmPasswordNameEditText)).check(matches(setError(getString(R.string.invalid_password))))
    }

    @Test
    fun matchPassword() {

        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(
            typeText("demo@demo.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordNameEditText)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordNameEditText)).perform(
            typeText("1234567"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.confirmPasswordNameEditText)).check(matches(setError(getString(R.string.password_not_match))))
    }

    @Test
    fun registerSuccess() {
        onView(withId(R.id.firstNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.lastNameEditText)).perform(typeText("Max"), closeSoftKeyboard())
        onView(withId(R.id.emailEditText)).perform(
            typeText("demo@demo.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordNameEditText)).perform(typeText("123456"), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordNameEditText)).perform(
            typeText("123456"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.registerButton)).perform(click())
        onView(withId(R.id.welcomeText)).check(matches(withText("Hi registration successfully done !")))
    }

    @Before
    @Throws
    fun setUp() {
        setFailureHandler(CustomFailureHandler(InstrumentationRegistry.getInstrumentation().targetContext))
    }


    private fun setError(expected: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Not found error message$expected, find it!")
            }

            override fun matchesSafely(v: View): Boolean {
                return if (v is EditText) {
                    (v as EditText).error.toString() == expected
                } else false
            }
        };
    }

    private fun getString(@StringRes resourceId: Int): String {
        return activityRule.activity.getString(resourceId)
    }


    fun withHintColor(expectedColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, EditText>(EditText::class.java) {
            override fun matchesSafely(editText: EditText): Boolean {
                return expectedColor == editText.currentHintTextColor
            }

            override fun describeTo(description: Description) {
                description.appendText("First name required: $expectedColor")
            }
        }
    }


    inner class CustomFailureHandler(targetContext: Context) : FailureHandler {
        private val delegate: FailureHandler

        init {
            delegate = DefaultFailureHandler(targetContext)
        }

        override fun handle(error: Throwable, viewMatcher: Matcher<View>) {
            try {
                delegate.handle(error, viewMatcher)
                //    ScreenshotWatcher()
            } catch (e: NoMatchingViewException) {
                // For example save device dump, take screenshot, etc.
                throw e
            }

        }
    }
/*
    inner class ScreenshotWatcher : TestWatcher() {

        override fun succeeded(description: org.junit.runner.Description?) {

        }
        override fun failed(e: Throwable?, description: org.junit.runner.Description?) {
            try {
                captureScreenshot(testName.methodName)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }

    @Throws
    fun captureScreenshot(name: String) {
        val capture: ScreenCapture = Screenshot.capture()
        capture.setFormat(Bitmap.CompressFormat.PNG)
        capture.setName(name)
        capture.process()

    }*/

}