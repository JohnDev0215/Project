package com.android.internal.view;

import android.graphics.Rect;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
interface ScrollCaptureViewHelper<V extends View> {
    public static final int DOWN = 1;
    public static final int UP = -1;

    void onPrepareForEnd(V v);

    void onPrepareForStart(V v, Rect rect);

    Rect onScrollRequested(V v, Rect rect, Rect rect2);

    default boolean onAcceptSession(V v) {
        return v != null && v.isVisibleToUser() && (v.canScrollVertically(-1) || v.canScrollVertically(1));
    }

    default Rect onComputeScrollBounds(V v) {
        return new Rect(v.getPaddingLeft(), v.getPaddingTop(), v.getWidth() - v.getPaddingRight(), v.getHeight() - v.getPaddingBottom());
    }
}
