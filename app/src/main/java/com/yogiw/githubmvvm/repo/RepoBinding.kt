package com.yogiw.githubmvvm.repo

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.yogiw.githubmvvm.data.RepoData

object RepoBinding {
    @BindingAdapter("app:repoList")
    @JvmStatic
    fun setRepoList(recyclerView: RecyclerView, repoData: MutableList<RepoData>) {
        with(recyclerView.adapter as RepoAdapter){
            replaceData(repoData)
        }
    }
}