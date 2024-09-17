package org.jibs.radio.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.*


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // TODO: Move to debug sourceset
        Timber.plant(DebugTree())
    }
}
