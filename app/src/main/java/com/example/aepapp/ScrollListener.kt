package com.example.aepapp

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScrollListener(private val layoutManager: LinearLayoutManager, private val onScroll: Unit) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5

    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(dy > 0){
            visibleItemCount = layoutManager.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if(loading){
                //TODO This is wrong. Its not a good check if the call was made or not
                if(totalItemCount >= previousTotal){
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if(!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)){
                loading = true
                onScroll
            }
        }

    }
}