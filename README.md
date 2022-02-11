# OnScrollListener

Step 1 : Add Jitpack repositry to your root level build.gradle
--
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}

Step 2 : Add the dependency
--
dependencies {
	        implementation 'com.github.akshaybhongale:OnScrollListener:1.1'
	}


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
