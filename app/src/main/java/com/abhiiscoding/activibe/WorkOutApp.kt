package com.abhiiscoding.activibe

import android.app.Application

class WorkOutApp: Application() {
    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}