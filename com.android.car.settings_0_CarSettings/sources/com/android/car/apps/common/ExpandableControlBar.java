package com.android.car.apps.common;

/* JADX INFO: loaded from: classes5.dex */
public interface ExpandableControlBar extends CarControlBar {

    public interface ExpandCollapseCallback {
        void onExpandCollapse(boolean z);
    }

    void close();

    void registerExpandCollapseCallback(ExpandCollapseCallback expandCollapseCallback);
}
