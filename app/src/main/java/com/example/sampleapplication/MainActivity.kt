package com.example.sampleapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            DataSource()
                .getPokemonListFlow()
//                .onLoadingState {
//                    Log.d("ThaerOutput", "Loading")
//                    (findViewById<TextView>(R.id.tv_Loading)).visibility = View.VISIBLE
//                }
                .onSuccessState {
                    (findViewById<TextView>(R.id.tv_parent)).visibility = View.VISIBLE
                    (findViewById<TextView>(R.id.tv_parent)).text = it.toString()
                }
                .onErrorState {
                    (findViewById<TextView>(R.id.tv_parent)).visibility = View.VISIBLE
                    (findViewById<TextView>(R.id.tv_parent)).text = it
                }
                .smartCollect()
//                .observeInLifecycle(this@MainActivity)

        }

    }

    //To be moved to a base fragment
    fun <T:Any> Flow<StatefulData<T>>.smartCollect() {
        this.onLoadingState {
            Log.d("ThaerOutput", "Loading")
            (findViewById<TextView>(R.id.tv_Loading)).visibility = View.VISIBLE
        }
            .onSuccessState {
                (findViewById<TextView>(R.id.tv_Loading)).visibility = View.GONE
            }
            .onErrorState {
                (findViewById<TextView>(R.id.tv_Loading)).visibility = View.GONE
            }
            .observeInLifecycle(this@MainActivity)
//            .collect()
    }

}
