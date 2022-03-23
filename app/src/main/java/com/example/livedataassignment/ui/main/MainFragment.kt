package com.example.livedataassignment.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.livedataassignment.R
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        class resultObserver : Observer<Int> {
            override fun onChanged(t: Int?) {
                tvResult.text = t.toString()
            }
        }

        val myResultObserver = resultObserver()

        viewModel.getResult().observe(viewLifecycleOwner, myResultObserver)

        button.setOnClickListener {
            if(editTextFirstNumber.text.isNotEmpty() && editTextSecondNumber.text.isNotEmpty()) {
                viewModel.multiply(editTextFirstNumber.text.toString(), editTextSecondNumber.text.toString())
            }
            else {
                tvResult.text = "No Product"
            }
        }
    }

}