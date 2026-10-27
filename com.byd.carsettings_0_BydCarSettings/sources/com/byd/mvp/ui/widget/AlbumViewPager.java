package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import b.b.m.c.a;

/* JADX INFO: loaded from: classes2.dex */
public class AlbumViewPager extends ViewPager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3770a;

    public AlbumViewPager(@NonNull Context context) {
        super(context);
        this.f3770a = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "--dispatchTouchEvent-----ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "mChildIsDragged = " + this.f3770a + ", ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        if (this.f3770a) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        return super.onTouchEvent(motionEvent);
    }

    public AlbumViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3770a = true;
    }
}
