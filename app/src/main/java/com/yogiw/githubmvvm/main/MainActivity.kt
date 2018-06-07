package com.yogiw.githubmvvm.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.yogiw.githubmvvm.R
import com.yogiw.githubmvvm.util.replaceFragmentInActivity
import com.yogiw.githubmvvm.util.obtainViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mActivity = this

        setupFragment()
        setupViewModel()

    }


    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameMain)
        MainFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameMain)
        }
    }


    private fun setupViewModel() {
        viewModel = obtainViewModel().apply{
            openRepo.observe(this@MainActivity, Observer{
                Toast.makeText(mActivity, it?.name, Toast.LENGTH_LONG).show()
            })
        }
    }

    fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
