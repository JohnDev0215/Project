package com.amazon.podcast.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* JADX INFO: loaded from: classes5.dex */
public final class WrappedListView extends ListView {
    public WrappedListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
