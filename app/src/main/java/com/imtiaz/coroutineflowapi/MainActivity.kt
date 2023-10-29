package com.imtiaz.coroutineflowapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

}


/*     Kotlin Flow - map { } Operator
*
*
* class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = flowOf(1,2,3,4,5).flowOn(Dispatchers.IO)

        runBlocking {
            data.map {data->
                data*data
            }.collect{
                Log.d("main", "onCreate: $it")
            }
        }

    }

}
*
* */

/*
*
* class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*asFlow - listOf data converted in flow*/
        val data = listOf(1,2,3,4,5,6,7,8).asFlow().flowOn(Dispatchers.IO)

        runBlocking {
            data.collect{
                Log.d("main", "onCreate: $it")
            }
        }

    }

}
*
*
* */



/*     Flow - flowOf {} method
*
*
*  class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*if i need to store list of data then i can store it flow of*/

        val data = flowOf(1,2,3,4,5,6,9,7).flowOn(Dispatchers.IO)

        runBlocking {
            data.collect{
                Log.d("main", "onCreate: $it")
            }
        }


    }

}*/


/*
************  Flow - flow {} builder block  ***********
*
*
* class MainActivity : AppCompatActivity() {
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
*
* */