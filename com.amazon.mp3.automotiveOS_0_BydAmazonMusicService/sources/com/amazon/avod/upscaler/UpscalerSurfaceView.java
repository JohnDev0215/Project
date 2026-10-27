package com.amazon.avod.upscaler;

import com.amazon.avod.media.playback.android.FixedDimensionView;
import com.myelin.library.FoveaSurfaceView;

/* JADX INFO: loaded from: classes2.dex */
public class UpscalerSurfaceView extends FoveaSurfaceView implements FixedDimensionView {
    private boolean mFitToScreen;
    private int mFixedHeight;
    private int mFixedWidth;
    private float mVideoAspectRatio;

    @Override // com.amazon.avod.media.playback.android.FixedDimensionView
    public void setFixedDimensions(int i, int i2) {
        this.mFixedWidth = i;
        this.mFixedHeight = i2;
        this.mFitToScreen = false;
        if (i > 0 && i2 > 0) {
            this.mVideoAspectRatio = i / i2;
        }
        requestLayout();
    }
}
