package com.woodsho.absoluteplan.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;

import com.woodsho.absoluteplan.AbsolutePlanApplication;
import com.woodsho.absoluteplan.R;

/**
 * Created by hewuzhao on 17/12/10.
 */

public class AbsPSharedPreference {
    private static final String NAME_SP = "absoluteplann_sp";
    private static final String NAME_LAST_SELECTED_SIDE_ID = "last_selected_side_id";
    private static final String NAME_SIDE_TITLE = "side_title";

    private volatile static AbsPSharedPreference sSPInstance = null;
    private SharedPreferences mSharedPreferences;

    private int mLastSelectedSideId;
    private String mSideTitle;

    private AbsPSharedPreference(Context context) {
        Resources res = context.getResources();
        mSharedPreferences = context.getSharedPreferences(NAME_SP, Context.MODE_PRIVATE);
        String id = mSharedPreferences.getString(NAME_LAST_SELECTED_SIDE_ID, String.valueOf(0));
        if (TextUtils.isEmpty(id)) {
            mLastSelectedSideId = 0;
        } else {
            mLastSelectedSideId = Integer.parseInt(id);
        }

        String defaultTitle = res.getString(R.string.wisdom);
        String title = mSharedPreferences.getString(NAME_SIDE_TITLE, defaultTitle);
        if (TextUtils.isEmpty(title)) {
            mSideTitle = defaultTitle;
        } else {
            mSideTitle = title;
        }
    }

    public static AbsPSharedPreference getInstanc() {
        if (sSPInstance == null) {
            synchronized (AbsPSharedPreference.class) {
                if (sSPInstance == null) {
                    sSPInstance = new AbsPSharedPreference(AbsolutePlanApplication.sAppContext);
                }
            }
        }
        return sSPInstance;
    }

    public void saveLastSelectedSideId(int id) {
        mLastSelectedSideId = id;
        mSharedPreferences.edit().putString(NAME_LAST_SELECTED_SIDE_ID, String.valueOf(id)).apply();
    }

    public int getLastSelectedSideId(int defaultValue) {
        return mLastSelectedSideId;
    }

    public void saveSideTitle(String title) {
        mSideTitle = title;
        mSharedPreferences.edit().putString(NAME_SIDE_TITLE, title).apply();
    }
    public String getSideTitle() {
        return mSideTitle;
    }
}
