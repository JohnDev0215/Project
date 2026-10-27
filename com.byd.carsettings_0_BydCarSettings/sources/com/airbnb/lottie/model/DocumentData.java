package com.airbnb.lottie.model;

import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class DocumentData {
    public final float baselineShift;

    @ColorInt
    public final int color;
    public final String fontName;
    public final Justification justification;
    public final float lineHeight;
    public final float size;

    @ColorInt
    public final int strokeColor;
    public final boolean strokeOverFill;
    public final float strokeWidth;
    public final String text;
    public final int tracking;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, float f2, Justification justification, int i, float f3, float f4, @ColorInt int i2, @ColorInt int i3, float f5, boolean z) {
        this.text = str;
        this.fontName = str2;
        this.size = f2;
        this.justification = justification;
        this.tracking = i;
        this.lineHeight = f3;
        this.baselineShift = f4;
        this.color = i2;
        this.strokeColor = i3;
        this.strokeWidth = f5;
        this.strokeOverFill = z;
    }

    public int hashCode() {
        int iHashCode = (((((int) ((((this.text.hashCode() * 31) + this.fontName.hashCode()) * 31) + this.size)) * 31) + this.justification.ordinal()) * 31) + this.tracking;
        long jFloatToRawIntBits = Float.floatToRawIntBits(this.lineHeight);
        return (((iHashCode * 31) + ((int) (jFloatToRawIntBits ^ (jFloatToRawIntBits >>> 32)))) * 31) + this.color;
    }
}
