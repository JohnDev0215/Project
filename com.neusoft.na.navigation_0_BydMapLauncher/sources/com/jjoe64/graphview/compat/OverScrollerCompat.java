package com.jjoe64.graphview.compat;

import android.widget.OverScroller;

/* JADX INFO: loaded from: classes2.dex */
public class OverScrollerCompat {
    private OverScrollerCompat() {
    }

    public static float getCurrVelocity(OverScroller overScroller) {
        return overScroller.getCurrVelocity();
    }
}
