package com.yogiw.githubmvvm.repo

import com.yogiw.githubmvvm.data.RepoData

interface ReposItemActionListener {
    fun onRepoClicked(repo: RepoData)
}