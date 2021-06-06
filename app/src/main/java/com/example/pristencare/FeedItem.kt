package com.example.pristencare

import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
sealed class FeedItem(private val layoutId: Int) : IRecyclerItemViewModel {

    override fun getLayoutId(): Int = layoutId

}


@ExperimentalCoroutinesApi
class ImageItem() : FeedItem(R.layout.image_item) {
    override fun getBindingPairs(): List<Pair<Int, Any>> {
        return emptyList()
    }

}