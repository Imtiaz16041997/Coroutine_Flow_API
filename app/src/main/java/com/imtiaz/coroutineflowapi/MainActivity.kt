package com.imtiaz.coroutineflowapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Data Collect inside the Main Thread, collect is a suspend func*/
        /*for the demo purpose i am using runBlocking, but in Production runBlocking might not need*/
        runBlocking {
            getData().catch {e->
                Log.d("main", "onCreate: ${e.message}")
            }

                    .collect{data->
            Log.d("main","onCreate: $data")
            }
        }

    }


    /*Data emit will happen in flow*/
     private fun getData(): kotlinx.coroutines.flow.Flow<Int> = flow{
            for (i in 1..5){
                delay(1000)
                emit(i)
            }
    }.flowOn(Dispatchers.IO) /*whenever any data which need to emit so it will execute  inside the background thread, IO works inside the background thread*/



}