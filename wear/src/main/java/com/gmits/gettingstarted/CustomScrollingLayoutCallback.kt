package com.gmits.gettingstarted

import android.support.v7.widget.RecyclerView
import android.support.wear.widget.WearableLinearLayoutManager
import android.view.View

class CustomScrollingLayoutCallback : WearableLinearLayoutManager.LayoutCallback() {
    override fun onLayoutFinished(child: View, parent: RecyclerView) {

        val centerOffset = child.height.toFloat() / 2.0f / parent.height.toFloat()
        val yRelativeToCenterOffset = child.y / parent.height + centerOffset
        val progresstoCenter = Math.sin(yRelativeToCenterOffset * Math.PI).toFloat()
        var mProgressToCenter = Math.abs(0.5f - yRelativeToCenterOffset)
        mProgressToCenter = Math.min(mProgressToCenter, MAX_ICON_PROGRESS)
        child.scaleX = 1 - mProgressToCenter
        child.scaleY = 1 - mProgressToCenter
        child.x = +(1 - progresstoCenter) * 50
    }

    companion object {

        private val MAX_ICON_PROGRESS = 2f
    }

}