package com.android.systemui.stackdivider.view.help;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes.dex */
public class NoScrollViewPager extends ViewPager {
    private boolean mCanScroll;

    public NoScrollViewPager(Context context) {
        super(context);
        this.mCanScroll = true;
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCanScroll = true;
    }

    public void setScanScroll(boolean isCanScroll) {
        this.mCanScroll = isCanScroll;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mCanScroll && super.onInterceptTouchEvent(ev);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        return this.mCanScroll && super.onTouchEvent(ev);
    }
}
