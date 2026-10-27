package com.android.launcher3.home.viewpage;

import android.content.Context;
import android.widget.Scroller;

/* JADX INFO: loaded from: classes4.dex */
class ViewPagerScroller extends Scroller {
    private int scrollDuration;

    ViewPagerScroller(Context context) {
        super(context);
        this.scrollDuration = 600;
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, this.scrollDuration);
    }

    @Override // android.widget.Scroller
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, this.scrollDuration);
    }

    void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }
}
