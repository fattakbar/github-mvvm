package internship.gits.newsapps.util

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.annotation.VisibleForTesting
import com.yogiw.githubmvvm.data.source.MainDataRepository
import com.yogiw.githubmvvm.main.MainViewModel
import com.yogiw.githubmvvm.repo.RepoViewModel
import com.yogiw.githubmvvm.util.Injection

class ViewModelFactory private constructor(
        private val application: Application,
        private val mainDataRepository: MainDataRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>)= with(modelClass) {
        when{
            isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(application,mainDataRepository)
            isAssignableFrom(RepoViewModel::class.java) ->
                RepoViewModel(application, mainDataRepository)
            else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE
                        ?: synchronized(ViewModelFactory::class.java){
                    INSTANCE
                            ?: ViewModelFactory(
                                    application, Injection.providedMainDataRepository(application.applicationContext))
                            .also { INSTANCE = it }
                }

        @VisibleForTesting fun destroyInstance(){
            INSTANCE = null
        }
    }
}