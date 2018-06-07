package com.yogiw.githubmvvm.repo

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yogiw.githubmvvm.R
import com.yogiw.githubmvvm.data.RepoData
import com.yogiw.githubmvvm.databinding.RepoItemBinding

class RepoAdapter(private var repoData: MutableList<RepoData>,private var repoViewModel: RepoViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val repoItemBinding : RepoItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.repo_item,parent,false)
        return RepoHolder(repoItemBinding)
    }

    override fun getItemCount(): Int = repoData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datas = repoData[position]
        val actionListener = object : ReposItemActionListener{
            override fun onRepoClicked(repo: RepoData) {
                repoViewModel.openRepo.value = datas
            }


        }
        (holder as RepoHolder).bindRows(datas, actionListener)
    }

    fun replaceData(repoDa: MutableList<RepoData>){
        setList(repoDa)
    }

    fun setList(repoData: MutableList<RepoData>){
        this.repoData = repoData
        notifyDataSetChanged()
    }

    class RepoHolder(binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root){
        val repoItemBinding = binding

        fun bindRows(repoData: RepoData, userActionListener: ReposItemActionListener) {
            repoItemBinding.datas =  repoData
            // repoItemBinding = userActionListener
            repoItemBinding.executePendingBindings()
//            if(repoData.urlToImage!= null)
//                newsRowBinding.ivRowNewsImage.load(news.urlToImage!!){
//                    requestCreator -> requestCreator.fit().centerCrop()
//                }
        }
    }
}