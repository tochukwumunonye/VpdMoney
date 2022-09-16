package com.example.vpdmoney.application

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.vpdmoney.presentation.detail.DetailFragment
import com.example.vpdmoney.presentation.list.UserFragment
import javax.inject.Inject


class FragmentFactory @Inject constructor(): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            UserFragment::class.java.name -> UserFragment()
            DetailFragment::class.java.name -> DetailFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}