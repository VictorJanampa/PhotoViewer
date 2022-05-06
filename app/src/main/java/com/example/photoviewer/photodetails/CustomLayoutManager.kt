package com.example.photoviewer.photodetails

import android.content.Context
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler


class CustomLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    private var mPendingTargetPos = -1
    private var mPendingPosOffset = -1

    override fun onLayoutChildren(recycler: Recycler?, state: RecyclerView.State) {
        if (mPendingTargetPos != -1 && state.itemCount > 0) {
            scrollToPositionWithOffset(mPendingTargetPos, mPendingPosOffset)
            mPendingTargetPos = -1
            mPendingPosOffset = -1
        }
        super.onLayoutChildren(recycler, state)
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        mPendingTargetPos = -1
        mPendingPosOffset = -1
        super.onRestoreInstanceState(state)
    }

    /**
     * Sets a start position that will be used **as soon as data is available**.
     * May be used if your Adapter starts with itemCount=0 (async data loading) but you need to
     * set the start position already at this time. As soon as itemCount > 0,
     * it will set the scrollPosition, so that given itemPosition is visible.
     * @param position
     * @param offset
     */

    fun setTargetStartPos(position: Int, offset: Int) {
        mPendingTargetPos = position
        mPendingPosOffset = offset
    }
}