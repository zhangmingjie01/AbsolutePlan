package com.woodsho.absoluteplan.ui;

import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.woodsho.absoluteplan.R;
import com.woodsho.absoluteplan.adapter.OpenSourceAdapter;
import com.woodsho.absoluteplan.bean.OpenSource;
import com.woodsho.absoluteplan.skinloader.SkinBaseActivity;
import com.woodsho.absoluteplan.skinloader.SkinManager;
import com.woodsho.absoluteplan.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

public class OpenSourceActivity extends SkinBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source);
        setupActionBar();
        StatusBarUtil.setColor(this, SkinManager.getInstance().getColor(R.color.colorPrimary), 0);
        init();
    }

    private void setupActionBar() {
        ViewGroup rootView = (ViewGroup) findViewById(R.id.action_bar_root); //id from appcompat

        if (rootView != null) {
            View view = getLayoutInflater().inflate(R.layout.opensource_toolbar_layout, rootView, false);
            rootView.addView(view, 0);

            Toolbar toolbar = (Toolbar) findViewById(R.id.opensource_toolbar);
            setSupportActionBar(toolbar);
            ImageView back = (ImageView) findViewById(R.id.back_opensource);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.opensource_recyclerview);
        OpenSourceAdapter sourceAdapter = new OpenSourceAdapter(this, getOpenSource());
        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        recyclerView.setAdapter(sourceAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }

    private List<OpenSource> getOpenSource() {
        List<OpenSource> list = new ArrayList<>();
        list.add(new OpenSource("NCalendar", "yannecer", "一款仿miui，仿小米，日历，周日历，月日历，月视图、周视图滑动切换，农历,Andriod Calendar , MIUI Calendar,小米日历", "https://github.com/yannecer/NCalendar"));
        list.add(new OpenSource("SwipeDelMenuLayout", "mcxtzhang", "The most simple SwipeMenu in the history, 0 coupling, support any ViewGroup. Step integration swipe (delete) menu, high imitation QQ, iOS.", "https://github.com/mcxtzhang/SwipeDelMenuLayout"));
        list.add(new OpenSource("NewbieGuide", "huburt-Hu", "Android 快速实现新手引导层的库", "https://github.com/huburt-Hu/NewbieGuide"));
        list.add(new OpenSource("MaterialDateTimePicker", "wdullaer", "Pick a date or time on Android in style", "https://github.com/wdullaer/MaterialDateTimePicker"));
        list.add(new OpenSource("uCrop", "Yalantis", "Image Cropping Library for Android", "https://github.com/Yalantis/uCrop"));
        return list;
    }

    private class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace ;

        public SpaceItemDecoration(int space) {
            mSpace = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if(parent.getChildPosition(view) != 0)
                outRect.top = mSpace;
        }
    }
}
