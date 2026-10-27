package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import b.a.d.b.a;

/* JADX INFO: loaded from: classes2.dex */
public class AlbumViewPager extends ViewPager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f705b;

    public AlbumViewPager(@NonNull Context context) {
        super(context);
        this.f705b = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "--dispatchTouchEvent-----ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        a.a("AlbumViewPager", "mChildIsDragged = " + this.f705b + ", ev = " + MotionEvent.actionToString(motionEvent.getAction()));
        if (this.f705b) {
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
        this.f705b = true;
    }
}
