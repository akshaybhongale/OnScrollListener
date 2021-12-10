# OnScrollListener

Usage :
 --
        val mLayoutManger = LinearLayoutManager(applicationContext)
        val pageListener = OnScrollListener(mLayoutManger)
        pageListener.setOnNextPageListener(object : OnNextPageListener {
            override fun onNext() {
                recyclerView.postDelayed({    
                    adapter.updateList(mList, lastIndex, newSize)
                    pageListener.setLoadingStatus(false)
                }, 1000)
            }
        })

        recyclerView.apply {
            adapter = linearAdapter
            layoutManager = mLayoutManger
            setHasFixedSize(true)
            addOnScrollListener(pageListener)
        }
--
