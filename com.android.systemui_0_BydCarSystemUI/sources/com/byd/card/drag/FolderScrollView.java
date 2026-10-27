package com.byd.card.drag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes.dex */
public class FolderScrollView extends ScrollView {
    public FolderScrollView(Context context) {
        super(context);
    }

    public FolderScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FolderScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            super.onTouchEvent(ev);
            return false;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            super.onTouchEvent(ev);
            return false;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
