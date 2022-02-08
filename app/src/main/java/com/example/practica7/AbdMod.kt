package com.example.practica7

import android.app.Application
import com.google.android.gms.ads.MobileAds

class AbdMod : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
}