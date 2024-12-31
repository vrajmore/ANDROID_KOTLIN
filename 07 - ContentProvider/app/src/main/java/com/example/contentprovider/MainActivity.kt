package com.example.contentprovider

import android.R.attr.name
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    var listdata: ArrayList<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val uri = CTPR.ContactsProviderContract.Contacts.CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)
        listdata = java.util.ArrayList()
        var list = findViewById<ListView>(R.id.listView)

        cursor?.use {
            while (it.moveToNext()) {
                val displayName =
                    it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val phoneNumber =
                    it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))

                Log.d("TAG", "Name: $displayName, Phone: $phoneNumber")

                val fullcontact: String = "NAME - $displayName\nPHONE no - $phoneNumber"

                listdata?.add(fullcontact)
            }
        }

        Log.d("TAG", "onCreate: "+listdata )

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listdata!!)
        list?.setAdapter(adapter)


    }

}