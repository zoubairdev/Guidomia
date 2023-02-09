package com.example.guidomia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidomia.databinding.FragmentHomeBinding
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val parentList: MutableList<Parent>? = mutableListOf()
    private var childList: MutableList<Child>? = mutableListOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val jsonArray = JSONArray(loadJSONFromAsset())
            for (i in 0 until jsonArray.length()) {
                val carObject = jsonArray.getJSONObject(i)
                val model = carObject.getString("model")
                val price = carObject.getDouble("customerPrice")
                val rating = carObject.getInt("rating")
                val imgFile = carObject.getString("imgFile")
                val prosList = carObject.getJSONArray("prosList")
                val consList = carObject.getJSONArray("consList")
                val child = Child(jsonArrayToList(prosList),jsonArrayToList(consList))
                childList?.clear()
                childList?.add(child)
                parentList?.add(Parent(model, price,rating,imgFile,childList!!.toList()))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val adapter = ExpandableRecyclerViewAdapter(parentList!!.toList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter
    }

    private fun loadJSONFromAsset(): String? {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun jsonArrayToList(jsonArray: JSONArray): List<String> {
        return (0 until jsonArray.length()).map { jsonArray.getString(it) }
    }
}