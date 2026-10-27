package com.google.android.setupdesign.items;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public interface IItem {
    int getLayoutResource();

    boolean isEnabled();

    void onBindView(View view);
}
