package com.example.guidomia.viewmodels

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidomia.adapters.ExpandableRecyclerViewAdapter
import com.example.guidomia.databinding.FragmentHomeBinding
import com.example.guidomia.models.Child
import com.example.guidomia.models.Parent
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class HomeViewModel: ViewModel() {

    lateinit var adapter: ExpandableRecyclerViewAdapter

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
    
    fun populateUI(context: Context,binding : FragmentHomeBinding,parentList : MutableList<Parent>,childList :MutableList<Child>){
        try {
            val jsonArray = JSONArray(loadJSONFromAsset(context))
            val spinner1Values = mutableListOf<String>()
            val spinner2Values = mutableListOf<String>()
            spinner1Values.add("Any make")
            spinner2Values.add("Any model")
            for (i in 0 until jsonArray.length()) {
                val carObject = jsonArray.getJSONObject(i)
                val model = carObject.getString("model")
                val make = carObject.getString("make")
                val price = carObject.getDouble("customerPrice")
                val rating = carObject.getInt("rating")
                val imgFile = carObject.getString("imgFile")
                val prosList = carObject.getJSONArray("prosList")
                val consList = carObject.getJSONArray("consList")
                val child = Child(jsonArrayToList(prosList),jsonArrayToList(consList))
                childList?.clear()
                childList?.add(child)
                parentList?.add(Parent(model, make,price,rating,imgFile,childList!!.toList()))
                spinner1Values.add(make)
                spinner2Values.add(model)
            }
            adapter = ExpandableRecyclerViewAdapter(parentList!!.toList())
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = adapter
            val spinner1Adapter = ArrayAdapter(context, R.layout.simple_spinner_item, spinner1Values)
            spinner1Adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.spinner1.adapter = spinner1Adapter

            val spinner2Adapter = ArrayAdapter(context, R.layout.simple_spinner_item, spinner2Values)
            spinner2Adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            binding.spinner2.adapter = spinner2Adapter

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun jsonArrayToList(jsonArray: JSONArray): List<String> {
        return (0 until jsonArray.length()).map { jsonArray.getString(it) }
    }

    fun filterData(textMake : String,textModel: String){
        adapter.filter(textMake,textModel)
    }
}