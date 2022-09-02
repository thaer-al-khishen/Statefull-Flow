package com.example.sampleapplication

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

//fun <T: Any> statefulFlow(action: suspend () -> T): Flow<StatefulData<T>> {
//    return flow {
//        emit(StatefulData.Loading)
//        delay(1000L)
//        emit(StatefulData.Success(action.invoke()))
//    }.catch {
//        emit(StatefulData.Error(it.message ?: "Some error occured"))
//    }
//}
//        .shareIn(
//        CoroutineScope(Dispatchers.IO),
//        started = SharingStarted.WhileSubscribed(5000),
//        replay = 1)
//        .stateIn(
//            CoroutineScope(Dispatchers.IO),
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = StatefulData.Loading)
