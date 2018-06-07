package com.yogiw.githubmvvm.repo

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yogiw.githubmvvm.R
import com.yogiw.githubmvvm.data.RepoData
import com.yogiw.githubmvvm.databinding.RepoItemBinding

class RepoAdapter(private var repoData: MutableList<RepoData>,private var newsViewModel: RepoViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val repoItemBinding : RepoItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.repo_item,parent,false)
        return NewsRowHolder(repoItemBinding)
    }

    override fun getItemCount(): Int = repoData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datas = repoData[position]
        val actionListener = object : ReposItemActionListener{
            override fun onRepoClicked(repo: RepoData) {
                RepoViewModel.op.value = repoData
            }


        }
        (holder as NewsRowHolder).bindRows(datas,actionListener)
    }

    fun replaceData(repoDa: MutableList<RepoData>){
        setList(repoDa)
    }

    fun setList(repoData: MutableList<RepoData>){
        this.repoData = repoData
        notifyDataSetChanged()
    }

    class NewsRowHolder(binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root){
        val repoItemBinding = binding

        fun bindRows(repoData: RepoData, userActionListener: ReposItemActionListener) {
            repoItemBinding.datas =  news
            repoItemBinding.action = userActionListener
            repoItemBinding.executePendingBindings()
            if(news.urlToImage!= null)
                newsRowBinding.ivRowNewsImage.load(news.urlToImage!!){
                    requestCreator -> requestCreator.fit().centerCrop()
                }
        }
    }
}