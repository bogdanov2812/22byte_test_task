package com.bogdanov.test22byte.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bogdanov.test22byte.R

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val apiKey = "aca5dd33a8534a51bd604c5546cdb23d"
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

}