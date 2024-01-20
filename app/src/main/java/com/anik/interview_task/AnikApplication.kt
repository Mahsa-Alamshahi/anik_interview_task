package com.anik.interview_task

import androidx.multidex.MultiDexApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AnikApplication(): MultiDexApplication(){

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}