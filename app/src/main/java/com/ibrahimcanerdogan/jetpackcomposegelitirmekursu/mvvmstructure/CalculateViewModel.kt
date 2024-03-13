package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.mvvmstructure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculateViewModel : ViewModel() {
    private var result = MutableLiveData<Int>()
    var resultData : LiveData<Int> = result

    init {
        result.value = 0
    }

    fun calculateSum(num1: String, num2: String) {
        result.value = num1.toInt() + num2.toInt()
    }

    fun calculateMultiplication(num1: String, num2: String) {
        result.value = num1.toInt() * num2.toInt()
    }
}