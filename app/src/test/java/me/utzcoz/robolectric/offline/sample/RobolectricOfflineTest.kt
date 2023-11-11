package me.utzcoz.robolectric.offline.sample

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class RobolectricOfflineTest {
    @Test
    @Config(minSdk = 24)
    fun `test lunch activity`() {
        val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
        assertNotNull(controller)
        controller.close()
    }
}