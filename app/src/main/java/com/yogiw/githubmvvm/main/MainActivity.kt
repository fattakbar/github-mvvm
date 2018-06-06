package com.yogiw.githubmvvm.main

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yogiw.githubmvvm.R

class MainActivity : AppCompatActivity() {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mActivity = this

    }


    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameMain)
        MainFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameMain)
        }
    }


    private fun setupViewModel() {
        viewModel = obtainViewModel().apply{
            openRepo.observe(this@MainActivity, Observer{ news ->
                onNewsClicked(news!!)
            })
        }
    }

    fun obtainViewModel(): MainViewModel = obtainViewModel(MainViewModel::class.java)
}
