package com.byd.view;

import android.widget.Checkable;

/* JADX INFO: loaded from: classes2.dex */
public interface TabContainer extends Checkable {
    int getViewId();

    void setOnCheckedChangeListener(TabContainerCheckChangeListener tabContainerCheckChangeListener);
}
