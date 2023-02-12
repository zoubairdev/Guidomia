package com.example.guidomia.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guidomia.adapters.ExpandableRecyclerViewAdapter
import com.example.guidomia.databinding.FragmentHomeBinding
import com.example.guidomia.models.Child
import com.example.guidomia.models.Parent
import com.example.guidomia.viewmodels.HomeViewModel
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val parentList: MutableList<Parent>? = mutableListOf()
    private var childList: MutableList<Child>? = mutableListOf()
    private lateinit var viewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.populateUI(requireActivity(),binding,parentList!!,childList!!)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}