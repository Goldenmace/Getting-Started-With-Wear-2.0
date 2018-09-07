package com.gmits.gettingstarted;

import android.graphics.Color;
import android.os.Bundle;
import android.support.wear.widget.WearableLinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WatchViewStub;

public class MainActivity extends WearableActivity {

    private WearableRecyclerView recycler_launcher_view;
    private CustomRecyclerAdapter customRecyclerAdapter;
    private CustomScrollingLayoutCallback customScrollingLayoutCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAmbientEnabled();

        final WatchViewStub stub = findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {

                recycler_launcher_view = stub.findViewById(R.id.recycler_launcher_view);
                recycler_launcher_view.setEdgeItemsCenteringEnabled(true);
                customScrollingLayoutCallback = new CustomScrollingLayoutCallback();
                WearableLinearLayoutManager wearableLinearLayoutManager = new WearableLinearLayoutManager(MainActivity.this, customScrollingLayoutCallback);
                recycler_launcher_view.setLayoutManager(wearableLinearLayoutManager);
                recycler_launcher_view.setCircularScrollingGestureEnabled(true);
                recycler_launcher_view.setBezelFraction(0.5f);
                recycler_launcher_view.setScrollDegreesPerScreen(90);

                customRecyclerAdapter = new CustomRecyclerAdapter();
                customRecyclerAdapter.setOnItemSelectListener(new CustomRecyclerAdapter.OnItemClick() {
                    @Override
                    public void onItemColorSelect(String colorCode) {
                        recycler_launcher_view.setBackgroundColor(Color.parseColor(colorCode));
                    }
                });

                recycler_launcher_view.setAdapter(customRecyclerAdapter);
            }
        });
    }
}
