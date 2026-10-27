package org.chromium.content.browser;

/* JADX INFO: loaded from: classes3.dex */
public class RenderCoordinates {
    private float mContentHeightCss;
    private float mContentOffsetYPix;
    private float mContentWidthCss;
    private float mDeviceScaleFactor;
    private boolean mHasFrameInfo;
    private float mLastFrameViewportHeightCss;
    private float mLastFrameViewportWidthCss;
    private float mScrollXCss;
    private float mScrollYCss;
    private float mPageScaleFactor = 1.0f;
    private float mMinPageScaleFactor = 1.0f;
    private float mMaxPageScaleFactor = 1.0f;

    void reset() {
        this.mScrollYCss = 0.0f;
        this.mScrollXCss = 0.0f;
        this.mPageScaleFactor = 1.0f;
        this.mHasFrameInfo = false;
    }

    void updateContentSizeCss(float f, float f2) {
        this.mContentWidthCss = f;
        this.mContentHeightCss = f2;
    }

    void setDeviceScaleFactor(float f) {
        this.mDeviceScaleFactor = f;
    }

    void updateFrameInfo(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        this.mScrollXCss = f;
        this.mScrollYCss = f2;
        this.mPageScaleFactor = f7;
        this.mMinPageScaleFactor = f8;
        this.mMaxPageScaleFactor = f9;
        this.mContentOffsetYPix = f10;
        updateContentSizeCss(f3, f4);
        this.mLastFrameViewportWidthCss = f5;
        this.mLastFrameViewportHeightCss = f6;
        this.mHasFrameInfo = true;
    }

    public void setFrameInfoForTest(float f, float f2) {
        reset();
        this.mDeviceScaleFactor = f;
        this.mContentOffsetYPix = f2;
    }

    public class NormalizedPoint {
        private float mXAbsoluteCss;
        private float mYAbsoluteCss;

        private NormalizedPoint() {
        }

        public float getXAbsoluteCss() {
            return this.mXAbsoluteCss;
        }

        public float getYAbsoluteCss() {
            return this.mYAbsoluteCss;
        }

        public float getXLocalDip() {
            return (this.mXAbsoluteCss - RenderCoordinates.this.mScrollXCss) * RenderCoordinates.this.mPageScaleFactor;
        }

        public float getYLocalDip() {
            return (this.mYAbsoluteCss - RenderCoordinates.this.mScrollYCss) * RenderCoordinates.this.mPageScaleFactor;
        }

        public float getXPix() {
            return getXLocalDip() * RenderCoordinates.this.mDeviceScaleFactor;
        }

        public float getYPix() {
            return (getYLocalDip() * RenderCoordinates.this.mDeviceScaleFactor) + RenderCoordinates.this.mContentOffsetYPix;
        }

        public void setAbsoluteCss(float f, float f2) {
            this.mXAbsoluteCss = f;
            this.mYAbsoluteCss = f2;
        }

        public void setLocalDip(float f, float f2) {
            setAbsoluteCss((f / RenderCoordinates.this.mPageScaleFactor) + RenderCoordinates.this.mScrollXCss, (f2 / RenderCoordinates.this.mPageScaleFactor) + RenderCoordinates.this.mScrollYCss);
        }

        public void setScreen(float f, float f2) {
            setLocalDip(f / RenderCoordinates.this.mDeviceScaleFactor, f2 / RenderCoordinates.this.mDeviceScaleFactor);
        }
    }

    public NormalizedPoint createNormalizedPoint() {
        return new NormalizedPoint();
    }

    public float getScrollX() {
        return this.mScrollXCss;
    }

    public float getScrollY() {
        return this.mScrollYCss;
    }

    public float getScrollXPix() {
        return fromLocalCssToPix(this.mScrollXCss);
    }

    public float getScrollYPix() {
        return fromLocalCssToPix(this.mScrollYCss);
    }

    public int getScrollXPixInt() {
        return (int) Math.floor(getScrollXPix());
    }

    public int getScrollYPixInt() {
        return (int) Math.floor(getScrollYPix());
    }

    public float getContentWidthCss() {
        return this.mContentWidthCss;
    }

    public float getContentHeightCss() {
        return this.mContentHeightCss;
    }

    public float getContentWidthPix() {
        return fromLocalCssToPix(this.mContentWidthCss);
    }

    public float getContentHeightPix() {
        return fromLocalCssToPix(this.mContentHeightCss);
    }

    public int getContentWidthPixInt() {
        return (int) Math.ceil(getContentWidthPix());
    }

    public int getContentHeightPixInt() {
        return (int) Math.ceil(getContentHeightPix());
    }

    public float getLastFrameViewportWidthCss() {
        return this.mLastFrameViewportWidthCss;
    }

    public float getLastFrameViewportHeightCss() {
        return this.mLastFrameViewportHeightCss;
    }

    public float getLastFrameViewportWidthPix() {
        return fromLocalCssToPix(this.mLastFrameViewportWidthCss);
    }

    public float getLastFrameViewportHeightPix() {
        return fromLocalCssToPix(this.mLastFrameViewportHeightCss);
    }

    public int getLastFrameViewportWidthPixInt() {
        return (int) Math.ceil(getLastFrameViewportWidthPix());
    }

    public int getLastFrameViewportHeightPixInt() {
        return (int) Math.ceil(getLastFrameViewportHeightPix());
    }

    public float getContentOffsetYPix() {
        return this.mContentOffsetYPix;
    }

    public float getPageScaleFactor() {
        return this.mPageScaleFactor;
    }

    public float getMinPageScaleFactor() {
        return this.mMinPageScaleFactor;
    }

    public float getMaxPageScaleFactor() {
        return this.mMaxPageScaleFactor;
    }

    public float getDeviceScaleFactor() {
        return this.mDeviceScaleFactor;
    }

    public float getMaxHorizontalScrollPix() {
        return getContentWidthPix() - getLastFrameViewportWidthPix();
    }

    public float getMaxVerticalScrollPix() {
        return getContentHeightPix() - getLastFrameViewportHeightPix();
    }

    public int getMaxHorizontalScrollPixInt() {
        return (int) Math.floor(getMaxHorizontalScrollPix());
    }

    public int getMaxVerticalScrollPixInt() {
        return (int) Math.floor(getMaxVerticalScrollPix());
    }

    public boolean hasFrameInfo() {
        return this.mHasFrameInfo;
    }

    public float fromPixToDip(float f) {
        return f / this.mDeviceScaleFactor;
    }

    public float fromDipToPix(float f) {
        return f * this.mDeviceScaleFactor;
    }

    public float fromPixToLocalCss(float f) {
        return f / (this.mDeviceScaleFactor * this.mPageScaleFactor);
    }

    public float fromLocalCssToPix(float f) {
        return f * this.mPageScaleFactor * this.mDeviceScaleFactor;
    }
}
