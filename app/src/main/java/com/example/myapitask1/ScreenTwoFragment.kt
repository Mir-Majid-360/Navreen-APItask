package com.example.myapitask1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapitask1.databinding.ScreenTwoFragmentBinding

class ScreenTwoFragment : Fragment(){
    lateinit var screenTwoFragmentBinding: ScreenTwoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        screenTwoFragmentBinding = ScreenTwoFragmentBinding.inflate(layoutInflater,   container, false)
        return screenTwoFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        screenTwoFragmentBinding.backIV.setOnClickListener {
            findNavController().navigate(R.id.action_screenTwoFragment_to_screenOneFragment)
        }
    }

    companion object{
        fun newInstance() =ScreenTwoFragment()
    }

}