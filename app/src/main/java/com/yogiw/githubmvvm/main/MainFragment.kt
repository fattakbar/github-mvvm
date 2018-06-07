package com.yogiw.githubmvvm.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yogiw.githubmvvm.R
import com.yogiw.githubmvvm.databinding.FragmentMainBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)

        viewBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            vm = (activity as MainActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()

    }

    companion object {
        fun newInstance() = MainFragment().apply {

        }
    }


}
