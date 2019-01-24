package com.example.boundservices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var randomNumberGeneratorService: RandomNumberGeneratorService? = null
    private lateinit var connection: ServiceConnection
    var bounded = false
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connection = object : ServiceConnection{
            override fun onServiceDisconnected(componentName: ComponentName) {
                Log.d(TAG,"Service disconnected.")
                randomNumberGeneratorService = null
            }

            override fun onServiceConnected(componentName: ComponentName
                                            , service: IBinder) {
                Log.d(TAG, "Service connected.")
                randomNumberGeneratorService = (service as RandomNumberGeneratorService.RandomNumberGeneratorServiceBinder).service
            }

        }
        setupViews()
    }

    private fun setupViews() {
        bind_service.setOnClickListener {
            if(!bounded){
                bindService(Intent(this, RandomNumberGeneratorService::class.java),connection,Context.BIND_AUTO_CREATE)
                bounded = true
            }
        }
        get_random_number.setOnClickListener {
            if(!bounded){
                generated_random_number.text = getString(R.string.service_not_bound)
            }else{
                generated_random_number.text = randomNumberGeneratorService?.randomNumber.toString()
            }
        }
        unbind_service.setOnClickListener {
            unbindSafely()
        }
    }

    private fun unbindSafely() {
        if (bounded) {
            unbindService(connection)
            bounded = false
        }
    }

    override fun onDestroy() {
        unbindSafely()
        super.onDestroy()
    }
}
