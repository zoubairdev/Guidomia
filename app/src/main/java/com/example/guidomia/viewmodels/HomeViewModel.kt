package com.example.guidomia.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class HomeViewModel: ViewModel() {

    fun loadJSONFromAsset(context : Context): String? {
        var json: String? = null
        json = try {
            val iS: InputStream = context?.assets!!.open("car_list.json")
            val size: Int = iS.available()
            val buffer = ByteArray(size)
            iS.read(buffer)
            iS.close()
            String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}