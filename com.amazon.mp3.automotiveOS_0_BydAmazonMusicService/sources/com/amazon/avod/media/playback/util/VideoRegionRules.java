package com.amazon.avod.media.playback.util;

import com.amazon.avod.media.playback.util.VideoRegionBuilder;

/* JADX INFO: loaded from: classes2.dex */
public class VideoRegionRules {
    VideoRegionBuilder.HorizontalAlign mHorizontalAlign = VideoRegionBuilder.HorizontalAlign.CENTER;
    VideoRegionBuilder.VerticalAlign mVerticalAlign = VideoRegionBuilder.VerticalAlign.CENTER;
    Integer mWidth = null;
    Float mWidthPercent = null;
    Integer mHeight = null;
    Float mHeightPercent = null;
    Integer mTop = null;
    Float mTopPercent = null;
    Integer mBottom = null;
    Float mBottomPercent = null;
    Integer mLeft = null;
    Float mLeftPercent = null;
    Integer mRight = null;
    Float mRightPercent = null;

    public VideoRegionRules top(int i) {
        this.mTop = Integer.valueOf(i);
        this.mTopPercent = null;
        return this;
    }

    public VideoRegionRules left(int i) {
        this.mLeft = Integer.valueOf(i);
        this.mLeftPercent = null;
        return this;
    }
}
