package com.example.androidarchitecturesample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test fun clickAddPlant_OpensPlantList() {
        onView(withText(R.string.tab_promoted)).perform(click())
        onView(withId(R.id.promoted_recycler_view)).check(matches(isDisplayed()))

        onView(withText(R.string.tab_entries)).perform(click())
        onView(withId(R.id.entry_recycler_view)).check(matches(isDisplayed()))
    }
}
