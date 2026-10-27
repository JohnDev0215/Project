package com.byd.automultipletheme.widget;

import android.animation.TypeEvaluator;
import com.byd.automultipletheme.common.Const;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* JADX INFO: compiled from: ColorEvaluator.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J$\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016J0\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/byd/automultipletheme/widget/ColorEvaluator;", "Landroid/animation/TypeEvaluator;", "", "()V", "mCurrentBlue", "", "mCurrentGreen", "mCurrentRed", "evaluate", "fraction", "", "startValue", "endValue", "getCurrentColor", "startColor", "endColor", "colorDiff", "offset", "getHexString", "", Const.CONTENT_SYSTEM_SETTING_COLUMN, "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ColorEvaluator implements TypeEvaluator<Object> {
    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;

    private final int getCurrentColor(int startColor, int endColor, int colorDiff, int offset, float fraction) {
        int i;
        if (startColor > endColor) {
            i = (int) (startColor - ((fraction * colorDiff) - offset));
            if (i < endColor) {
                return endColor;
            }
        } else {
            i = (int) (startColor + ((fraction * colorDiff) - offset));
            if (i > endColor) {
                return endColor;
            }
        }
        return i;
    }

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String str = (String) startValue;
        String str2 = (String) endValue;
        Intrinsics.checkNotNull(str);
        String strSubstring = str.substring(1, 3);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        int i = Integer.parseInt(strSubstring, CharsKt.checkRadix(16));
        String strSubstring2 = str.substring(3, 5);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
        int i2 = Integer.parseInt(strSubstring2, CharsKt.checkRadix(16));
        String strSubstring3 = str.substring(5, 7);
        Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
        int i3 = Integer.parseInt(strSubstring3, CharsKt.checkRadix(16));
        Intrinsics.checkNotNull(str2);
        String strSubstring4 = str2.substring(1, 3);
        Intrinsics.checkNotNullExpressionValue(strSubstring4, "this as java.lang.String…ing(startIndex, endIndex)");
        int i4 = Integer.parseInt(strSubstring4, CharsKt.checkRadix(16));
        String strSubstring5 = str2.substring(3, 5);
        Intrinsics.checkNotNullExpressionValue(strSubstring5, "this as java.lang.String…ing(startIndex, endIndex)");
        int i5 = Integer.parseInt(strSubstring5, CharsKt.checkRadix(16));
        String strSubstring6 = str2.substring(5, 7);
        Intrinsics.checkNotNullExpressionValue(strSubstring6, "this as java.lang.String…ing(startIndex, endIndex)");
        int i6 = Integer.parseInt(strSubstring6, CharsKt.checkRadix(16));
        if (this.mCurrentRed == -1) {
            this.mCurrentRed = i;
        }
        if (this.mCurrentGreen == -1) {
            this.mCurrentGreen = i2;
        }
        if (this.mCurrentBlue == -1) {
            this.mCurrentBlue = i3;
        }
        int iAbs = Math.abs(i - i4);
        int iAbs2 = iAbs + Math.abs(i2 - i5);
        int iAbs3 = iAbs2 + Math.abs(i3 - i6);
        if (this.mCurrentRed != i4) {
            this.mCurrentRed = getCurrentColor(i, i4, iAbs3, 0, fraction);
        } else if (this.mCurrentGreen != i5) {
            this.mCurrentGreen = getCurrentColor(i2, i5, iAbs3, iAbs, fraction);
        } else if (this.mCurrentBlue != i6) {
            this.mCurrentBlue = getCurrentColor(i3, i6, iAbs3, iAbs2, fraction);
        }
        return '#' + getHexString(this.mCurrentRed) + getHexString(this.mCurrentGreen) + getHexString(this.mCurrentBlue);
    }

    private final String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = '0' + hexString;
        }
        Intrinsics.checkNotNullExpressionValue(hexString, "hexString");
        return hexString;
    }
}
