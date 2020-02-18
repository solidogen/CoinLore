package com.spyrdonapps.coinlore.ui

import android.app.Application
import com.spyrdonapps.coinlore.BuildConfig
import com.spyrdonapps.coinlore.ui.main.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import timber.log.Timber

class CoinLoreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@CoinLoreApp)
            androidLogger()
            loadModules()
        }
    }

    private fun KoinApplication.loadModules() {
        modules(
            listOf(mainModule)
        )
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
