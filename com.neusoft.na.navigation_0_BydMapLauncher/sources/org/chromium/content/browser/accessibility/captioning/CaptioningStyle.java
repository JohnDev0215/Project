package org.chromium.content.browser.accessibility.captioning;

import android.graphics.Typeface;
import android.view.accessibility.CaptioningManager;

/* JADX INFO: loaded from: classes3.dex */
public class CaptioningStyle {
    private Integer mBackgroundColor;
    private Integer mEdgeColor;
    private Integer mEdgeType;
    private Integer mForegroundColor;
    private Typeface mTypeface;
    private Integer mWindowColor;

    public CaptioningStyle(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Typeface typeface) {
        this.mBackgroundColor = num;
        this.mEdgeColor = num2;
        this.mEdgeType = num3;
        this.mForegroundColor = num4;
        this.mWindowColor = num5;
        this.mTypeface = typeface;
    }

    public Integer getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public Integer getEdgeColor() {
        return this.mEdgeColor;
    }

    public Integer getEdgeType() {
        return this.mEdgeType;
    }

    public Integer getForegroundColor() {
        return this.mForegroundColor;
    }

    public Integer getWindowColor() {
        return this.mWindowColor;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public static CaptioningStyle createFrom(CaptioningManager.CaptionStyle captionStyle) {
        if (captionStyle == null) {
            return new CaptioningStyle(null, null, null, null, null, null);
        }
        return new CaptioningStyle(captionStyle.hasBackgroundColor() ? Integer.valueOf(captionStyle.backgroundColor) : null, captionStyle.hasEdgeColor() ? Integer.valueOf(captionStyle.edgeColor) : null, captionStyle.hasEdgeType() ? Integer.valueOf(captionStyle.edgeType) : null, captionStyle.hasForegroundColor() ? Integer.valueOf(captionStyle.foregroundColor) : null, captionStyle.hasWindowColor() ? Integer.valueOf(captionStyle.windowColor) : null, captionStyle.getTypeface());
    }
}
