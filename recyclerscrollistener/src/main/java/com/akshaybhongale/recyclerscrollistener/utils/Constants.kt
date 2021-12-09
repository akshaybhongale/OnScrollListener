package com.akshaybhongale.recyclerscrollistener.utils

/**
 * constants for zero value
 */
const val ZERO = 0

/**
 * constant for default visible count
 */
const val DEFAULT_VISIBLE_COUNT = 15


/**
 * This method is used to get last visible item if recycler view has Staggered Grid layout
 * @param lastVisibleItemPositions array of last visible item
 * @return return item count
 */
fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
    var maxSize = ZERO
    for (i in lastVisibleItemPositions.indices) {
        if (i == ZERO) {
            maxSize = lastVisibleItemPositions[i]
        } else if (lastVisibleItemPositions[i] > maxSize) {
            maxSize = lastVisibleItemPositions[i]
        }
    }
    return maxSize
}