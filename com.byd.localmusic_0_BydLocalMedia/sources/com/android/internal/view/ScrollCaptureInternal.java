package com.android.internal.view;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.ScrollCaptureCallback;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollCaptureInternal {
    private static final int DOWN = 1;
    private static final String TAG = "ScrollCaptureInternal";
    public static final int TYPE_FIXED = 0;
    public static final int TYPE_RECYCLING = 2;
    public static final int TYPE_SCROLLING = 1;
    private static final int UP = -1;

    public static int detectScrollingType(View view) {
        if (!(view instanceof ViewGroup)) {
            return 0;
        }
        if (!view.canScrollVertically(1) && !view.canScrollVertically(-1)) {
            return 0;
        }
        if (((ViewGroup) view).getChildCount() > 1) {
            return 2;
        }
        if (view.getScrollY() != 0) {
            return 1;
        }
        if (view.canScrollVertically(-1)) {
            return 2;
        }
        view.scrollTo(view.getScrollX(), 1);
        if (view.getScrollY() != 1) {
            return 2;
        }
        view.scrollTo(view.getScrollX(), 0);
        return 1;
    }

    public ScrollCaptureCallback requestCallback(View view, Rect rect, Point point) {
        if (detectScrollingType(view) != 1) {
            return null;
        }
        return new ScrollCaptureViewSupport((ViewGroup) view, new ScrollViewCaptureHelper());
    }
}
