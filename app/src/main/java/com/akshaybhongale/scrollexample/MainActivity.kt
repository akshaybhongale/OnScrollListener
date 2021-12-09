package com.akshaybhongale.scrollexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshaybhongale.recyclerscrollistener.OnScrollListener
import com.akshaybhongale.recyclerscrollistener.listener.OnNextPageListener
import com.akshaybhongale.scrollexample.adapter.LinearAdapter
import com.akshaybhongale.scrollexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mList = getDummyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        val linearAdapter = LinearAdapter(mList)
        val mLayoutManger = LinearLayoutManager(applicationContext)
        val pageListener = OnScrollListener(mLayoutManger)
        pageListener.setOnNextPageListener(object : OnNextPageListener {
            override fun onNext() {

                binding.linearLayoutRecyclerView.postDelayed({
                    val lastIndex = mList.size
                    mList.addAll(getDummyList())
                    val newSize = mList.size
                    Log.d("onNext", "lastIndex $lastIndex , newSize $newSize")
                    linearAdapter.updateList(mList, lastIndex, newSize)
                    pageListener.setLoadingStatus(false)
                }, 1000)
            }
        })

        binding.linearLayoutRecyclerView.apply {
            adapter = linearAdapter
            layoutManager = mLayoutManger
            setHasFixedSize(true)
            addOnScrollListener(pageListener)
        }
    }
    fun getDummyList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0..20) {
            list.add("Dummy Item $i")
        }
        return list
    }

}