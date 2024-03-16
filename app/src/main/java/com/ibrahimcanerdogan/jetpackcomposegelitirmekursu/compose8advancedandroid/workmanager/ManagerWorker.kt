package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose8advancedandroid.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class ManagerWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val total = 10 + 20
        Log.i("ManagerWorker", total.toString())
        return Result.success()
    }
}