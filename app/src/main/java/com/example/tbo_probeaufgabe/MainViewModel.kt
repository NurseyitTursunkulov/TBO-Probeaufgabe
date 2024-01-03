package com.example.tbo_probeaufgabe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbo_probeaufgabe.data.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * MainViewModel
 */
class MainViewModel(
    val api:Api
):ViewModel() {
    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                api.getCoins().forEach {

                Log.d("nurs","${it.name}")
                }
            }
        }
    }
}