package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class AlbumViewPager extends ViewPager {

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f2827l0;

    public AlbumViewPager(Context context) {
        super(context);
        this.f2827l0 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "--dispatchTouchEvent-----ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "mChildIsDragged = " + this.f2827l0 + ", ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        if (this.f2827l0) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        return super.onTouchEvent(motionEvent);
    }

    public AlbumViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2827l0 = true;
    }
}
