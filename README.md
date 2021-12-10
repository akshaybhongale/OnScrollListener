# OnScrollListener

Usage :
 --
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
--
