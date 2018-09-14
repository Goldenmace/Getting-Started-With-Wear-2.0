package com.gmits.gettingstarted

import android.graphics.Color
import android.os.Bundle
import android.support.wear.widget.WearableLinearLayoutManager
import android.support.wear.widget.WearableRecyclerView
import android.support.wearable.activity.WearableActivity
import android.support.wearable.view.WatchViewStub

class MainActivity : WearableActivity() {

    private var recycler_launcher_view: WearableRecyclerView? = null
    private var customRecyclerAdapter: CustomRecyclerAdapter? = null
    private var customScrollingLayoutCallback: CustomScrollingLayoutCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAmbientEnabled()

        val stub = findViewById<WatchViewStub>(R.id.watch_view_stub)
        stub.setOnLayoutInflatedListener { stub ->
            recycler_launcher_view = stub.findViewById(R.id.recycler_launcher_view)
            recycler_launcher_view!!.isEdgeItemsCenteringEnabled = true
            customScrollingLayoutCallback = CustomScrollingLayoutCallback()
            val wearableLinearLayoutManager = WearableLinearLayoutManager(this@MainActivity, customScrollingLayoutCallback)
            recycler_launcher_view!!.layoutManager = wearableLinearLayoutManager
            recycler_launcher_view!!.isCircularScrollingGestureEnabled = true
            recycler_launcher_view!!.bezelFraction = 0.5f
            recycler_launcher_view!!.scrollDegreesPerScreen = 90f

            customRecyclerAdapter = CustomRecyclerAdapter()
            customRecyclerAdapter!!.setOnItemSelectListener(object : CustomRecyclerAdapter.OnItemClick {
                override fun onItemColorSelect(colorCode: String) {
                    recycler_launcher_view!!.setBackgroundColor(Color.parseColor(colorCode))
                }
            })

            recycler_launcher_view!!.adapter = customRecyclerAdapter
        }
    }
}
