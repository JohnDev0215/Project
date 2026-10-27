package com.amazon.mp3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistAndGenreDetailImageContainer extends LinearLayout {
    public PlaylistAndGenreDetailImageContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth / 2);
    }
}
