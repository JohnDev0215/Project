package com.amazon.avod.media.playback.util;

/* JADX INFO: loaded from: classes2.dex */
public class VideoRegion {
    private final int mFromLeft;
    private final int mFromTop;
    private final int mHeight;
    private final int mWidth;

    private static int interpolate(int i, int i2, float f) {
        return i + ((int) ((i2 - i) * f));
    }

    public VideoRegion(int i, int i2, int i3, int i4) {
        this.mFromTop = i;
        this.mFromLeft = i2;
        this.mWidth = i3;
        this.mHeight = i4;
    }

    public int getFromTop() {
        return this.mFromTop;
    }

    public int getFromLeft() {
        return this.mFromLeft;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public VideoRegion interpolate(VideoRegion videoRegion, float f) {
        return new VideoRegion(interpolate(getFromTop(), videoRegion.getFromTop(), f), interpolate(getFromLeft(), videoRegion.getFromLeft(), f), interpolate(getWidth(), videoRegion.getWidth(), f), interpolate(getHeight(), videoRegion.getHeight(), f));
    }

    public String toString() {
        return "[from top=" + this.mFromTop + ", from left=" + this.mFromLeft + ", width=" + this.mWidth + ", height=" + this.mHeight + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VideoRegion videoRegion = (VideoRegion) obj;
        return this.mFromTop == videoRegion.mFromTop && this.mFromLeft == videoRegion.mFromLeft && this.mWidth == videoRegion.mWidth && this.mHeight == videoRegion.mHeight;
    }

    public int hashCode() {
        return (((((this.mFromTop * 31) + this.mFromLeft) * 31) + this.mWidth) * 31) + this.mHeight;
    }
}
