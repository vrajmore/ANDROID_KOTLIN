package com.example.bluetoothbattery

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService


import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.reflect.Method
import java.util.UUID


class MainActivity : AppCompatActivity() {
    private lateinit var bluetooth: BluetoothAdapter

    companion object {
        private const val REQUEST_ENABLE_BT = 1
        val BATTERY_SERVICE_UUID = java.util.UUID.fromString("0000180F-0000-1000-8000-00805F9B34FB")
        val BATTERY_LEVEL_UUID = java.util.UUID.fromString("00002A19-0000-1000-8000-00805F9B34FB")
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn = findViewById<Button>(R.id.button)
        bluetooth = BluetoothAdapter.getDefaultAdapter()
        val pairedDevice = bluetooth.bondedDevices




        if (pairedDevice.isNotEmpty()){
            for (device in pairedDevice){
                val deviceaddress = device.address
                val devicename = device.name
                btn.setOnClickListener {
                    val device = bluetooth.getRemoteDevice("$deviceaddress")
                    val batterypercentage = getBatteryLevel(device)
                    Toast.makeText(this, "Name : $devicename\nbattery is $batterypercentage%", Toast.LENGTH_SHORT).show()
                }
            }
        }


        bluetoothcheckandonn()
        

    }

    fun bluetoothcheckandonn() {
        if (bluetooth == null) {
            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show()
        }

        if (!bluetooth.isEnabled) {
            val bluetoothenableintent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(bluetoothenableintent, REQUEST_ENABLE_BT)
        }
    }

    fun getBatteryLevel(pairedDevice: BluetoothDevice?): Int {
        return pairedDevice?.let { bluetoothDevice -> (bluetoothDevice.javaClass.getMethod("getBatteryLevel"))
                .invoke(pairedDevice) as Int
        } ?: -1
    }

}