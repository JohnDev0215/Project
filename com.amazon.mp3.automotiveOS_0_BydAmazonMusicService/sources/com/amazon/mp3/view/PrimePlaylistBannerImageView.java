package com.amazon.mp3.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes4.dex */
public class PrimePlaylistBannerImageView extends ImageView {
    public PrimePlaylistBannerImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec / 2);
    }
}
