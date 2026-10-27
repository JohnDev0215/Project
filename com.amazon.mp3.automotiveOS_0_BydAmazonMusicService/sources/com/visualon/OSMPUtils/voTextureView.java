package com.visualon.OSMPUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes7.dex */
public class voTextureView extends TextureView {
    private static final String TAG = "voTextureView.java";
    private int mVideoHeight;
    private int mVideoWidth;

    public voTextureView(Context context) {
        super(context);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    public voTextureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    public voTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        this.mVideoWidth = layoutParams.width;
        this.mVideoHeight = layoutParams.height;
        super.setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int defaultSize = View.getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = View.getDefaultSize(this.mVideoHeight, i2);
        int i4 = this.mVideoHeight;
        if (i4 <= 0 || (i3 = this.mVideoWidth) <= 0) {
            setMeasuredDimension(defaultSize, defaultSize2);
        } else {
            setMeasuredDimension(i3, i4);
        }
    }
}
