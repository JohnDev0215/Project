package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes2.dex */
public class NoScrollViewPager extends ViewPager {

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f2895l0;

    public NoScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2895l0 = true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f2895l0) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f2895l0) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void scrollBy(int i8, int i9) {
    }

    @Override // android.view.View
    public void scrollTo(int i8, int i9) {
    }

    public void setNoScroll(boolean z7) {
        this.f2895l0 = z7;
    }

    public NoScrollViewPager(Context context) {
        super(context);
        this.f2895l0 = true;
    }
}
