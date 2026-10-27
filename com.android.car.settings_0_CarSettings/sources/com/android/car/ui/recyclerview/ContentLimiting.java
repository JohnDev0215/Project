package com.android.car.ui.recyclerview;

/* JADX INFO: loaded from: classes6.dex */
public interface ContentLimiting {
    public static final int UNLIMITED = -1;

    int getConfigurationId();

    void setMaxItems(int i);

    void setScrollingLimitedMessageResId(int i);
}
