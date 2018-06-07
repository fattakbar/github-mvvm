package com.yogiw.githubmvvm.repo

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.yogiw.githubmvvm.R
import com.yogiw.githubmvvm.data.RepoData
import com.yogiw.githubmvvm.util.obtainViewModel
import com.yogiw.githubmvvm.util.replaceFragmentInActivity
import com.yogiw.githubmvvm.repo.ReposItemActionListener

class RepoActivity : AppCompatActivity() {


    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        mActivity = this
        setupViewModel()
        setupFragment()
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply{
            openRepo.observe(this@RepoActivity, Observer{
                Log.i("xx", " * it")
                onRepoClicked(it!!)
            })
        }
    }

    private fun setupFragment() {
        supportFragmentManager.findFragmentById(R.id.frameRepo)
        RepoFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameRepo)
        }
    }

    fun onRepoClicked(url: String) {
        Toast.makeText(this, "xx", Toast.LENGTH_LONG).show()
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(mActivity, R.color.colorPrimary))
        customTabsIntent.launchUrl(mActivity, Uri.parse(url))
    }

    fun obtainViewModel(): RepoViewModel = obtainViewModel(RepoViewModel::class.java)


}
