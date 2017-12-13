package com.woodsho.absoluteplan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hewuzhao on 17/12/9.
 */

public class PlanTask implements Parcelable {
    public String id;
    public int priority;
    public String title;
    public String describe;
    public long time;
    public int state; //0: normal; 1: finished
    public int color;
    public String fromList;

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcelable(Parcel in) {
        priority = in.readInt();
        title = in.readString();
        describe = in.readString();
        time = in.readLong();
        fromList = in.readString();
        state = in.readInt();
        id = in.readString();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(priority);
        dest.writeString(title);
        dest.writeString(describe);
        dest.writeLong(time);
        dest.writeString(fromList);
        dest.writeInt(state);
        dest.writeString(id);
        dest.writeInt(color);
    }

    public static final Creator<PlanTask> CREATOR = new Creator<PlanTask>() {
        @Override
        public PlanTask createFromParcel(Parcel in) {
            PlanTask planTask = new PlanTask();
            planTask.readFromParcelable(in);
            return planTask;
        }

        @Override
        public PlanTask[] newArray(int size) {
            return new PlanTask[size];
        }
    };
}