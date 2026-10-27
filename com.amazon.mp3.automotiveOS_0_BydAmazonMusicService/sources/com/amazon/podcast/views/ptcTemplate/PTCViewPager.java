package com.amazon.podcast.views.ptcTemplate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCViewPager extends ViewPager {
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(final MotionEvent event) {
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(final MotionEvent event) {
        return false;
    }

    public PTCViewPager(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
    }
}
