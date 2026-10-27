package com.android.systemui.stackdivider.view;

/* JADX INFO: loaded from: classes.dex */
public class SplitScreenEvent {
    public static final int EVENT_APP_LIST_CHANGE = 5;
    public static final int EVENT_SHOW_PRIMARY_SELECT_ANOTHER_APP_TOAST = 2;
    public static final int EVENT_SHOW_SECONDARY_SELECT_ANOTHER_APP_TOAST = 1;
    public static final int EVENT_WINDOWING_MODE_SPLIT_SCREEN_PRIMARY = 3;
    public static final int EVENT_WINDOWING_MODE_SPLIT_SCREEN_SECONDARY = 4;
    private int mData;
    private final int mSplitScreenEvent;

    public int getSplitScreenEvent() {
        return this.mSplitScreenEvent;
    }

    public int getData() {
        return this.mData;
    }

    public SplitScreenEvent(int event) {
        this.mSplitScreenEvent = event;
    }

    public SplitScreenEvent(int event, int data) {
        this.mSplitScreenEvent = event;
        this.mData = data;
    }
}
