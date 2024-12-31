package com.example.jetpackcompose_calculator

import android.content.Context
import android.util.Log
import androidx.collection.intFloatMapOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import org.mozilla.javascript.Scriptable

class calculatorviewmodel : ViewModel() {
    private val _datatext = MutableLiveData("")
    val datatext: LiveData<String> = _datatext
    private val _resulttext = MutableLiveData("")
    val resulttext: LiveData<String> = _resulttext


    fun onbtnclick(btn: String) {

        _datatext.value?.let {

            if (btn == "AC") {
                _datatext.value = ""
                _resulttext.value = "0"
                return
            }
            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _datatext.value = it.substring(0, it.length - 1)
                    return
                }
            }
            if (btn == "=") {
                _datatext.value = _resulttext.value
                return
            }
            _datatext.value = it + btn
                try {
                    _resulttext.value = calculateresult(_datatext.value.toString())
                } catch (_: Exception) { }
        }

    }

    fun calculateresult(data: String): String {
        val context: org.mozilla.javascript.Context = org.mozilla.javascript.Context.enter()
        context.optimizationLevel = -1
        val scriptable: Scriptable = context.initStandardObjects()
        val finalresult = context.evaluateString(scriptable, data, "Javascript", 1, null)
        return finalresult.toString()
    }
}