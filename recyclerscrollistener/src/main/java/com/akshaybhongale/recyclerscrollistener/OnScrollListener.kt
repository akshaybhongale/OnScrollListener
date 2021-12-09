package com.akshaybhongale.recyclerscrollistener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akshaybhongale.recyclerscrollistener.listener.OnNextPageListener
import com.akshaybhongale.recyclerscrollistener.utils.DEFAULT_VISIBLE_COUNT
import com.akshaybhongale.recyclerscrollistener.utils.ZERO
import com.akshaybhongale.recyclerscrollistener.utils.getLastVisibleItem

/**
 * This method is used to listen and handle vertical scroll listener
 * @param layoutManger layout manger of recyclerview
 */
class OnScrollListener<out T : RecyclerView.LayoutManager>(private val layoutManger: T) :
    RecyclerView.OnScrollListener() {

    /**
     * Default visible count
     */
    private var mVisibleCount = DEFAULT_VISIBLE_COUNT

    /**
     * Total items cont in recycler view
     */
    private var mTotalItems = ZERO

    /**
     * Last visible item index for maintaining scroll state
     */
    private var mLastVisibleItem = ZERO

    /**
     * loading status for maintaining recycler data
     */
    private var mIsLoading = false

    /**
     *  page listener to handle and notify scroll event
     */
    private lateinit var mPageListener: OnNextPageListener

    init {
        when (layoutManger) {
            is GridLayoutManager -> mVisibleCount *= layoutManger.spanCount
            is StaggeredGridLayoutManager -> mVisibleCount *= layoutManger.spanCount
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy <= ZERO) return
        mTotalItems = layoutManger.itemCount
        when (layoutManger) {
            is LinearLayoutManager -> mLastVisibleItem = layoutManger.findLastVisibleItemPosition()
            is GridLayoutManager -> mLastVisibleItem = layoutManger.findLastVisibleItemPosition()
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions = layoutManger.findLastVisibleItemPositions(null)
                mLastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
            }
        }
        if (!isLoading() && mTotalItems <= mLastVisibleItem + mVisibleCount) {
            mPageListener.onNext()
            setLoadingStatus(true)
        }
    }

    /**
     * This method is used to handle loading status of data set for recycler view
     * @param status loading status
     */
    fun setLoadingStatus(status: Boolean) {
        mIsLoading = status
    }

    /**
     * This method is used to get loading status
     * @return loading status
     */
    fun isLoading() = mIsLoading

    /**
     * This method is used to set page listener for recycler scroll
     * @param listener to listen recycler scroll
     */
    fun setOnNextPageListener(listener: OnNextPageListener) {
        mPageListener = listener
    }
}