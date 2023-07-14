package com.example.sprintm6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprintm6.ViewModel.PhonesViewModel
import com.example.sprintm6.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var mBinding: FragmentFirstBinding

    private val mViewModel:PhonesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentFirstBinding.inflate(inflater, container, false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter=PhonesAdapter()
        mBinding.recyclerview.adapter=adapter
        mBinding.recyclerview.layoutManager= GridLayoutManager(context,2)
        mViewModel.getPhonesList().observe(viewLifecycleOwner,{

            it?.let {
                adapter.update(it)
            }


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
}