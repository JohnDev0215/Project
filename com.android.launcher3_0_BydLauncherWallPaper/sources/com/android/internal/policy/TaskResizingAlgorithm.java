package com.android.internal.policy;

import android.graphics.Point;
import android.graphics.Rect;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public class TaskResizingAlgorithm {
    public static final int CTRL_BOTTOM = 8;
    public static final int CTRL_LEFT = 1;
    public static final int CTRL_NONE = 0;
    public static final int CTRL_RIGHT = 2;
    public static final int CTRL_TOP = 4;
    public static final float MIN_ASPECT = 1.2f;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CtrlType {
    }

    public static Rect resizeDrag(float f, float f2, float f3, float f4, Rect rect, int i, int i2, int i3, Point point, boolean z, boolean z2) {
        int iMax;
        int iMax2;
        int iMax3;
        int iMax4;
        int iMin;
        int i4;
        int iRound = Math.round(f - f3);
        int iRound2 = Math.round(f2 - f4);
        int i5 = rect.left;
        int i6 = rect.top;
        int i7 = rect.right;
        int i8 = rect.bottom;
        int i9 = i7 - i5;
        int i10 = i8 - i6;
        int i11 = i & 1;
        if (i11 != 0) {
            iMax = Math.max(i2, Math.min(i9 - iRound, point.x));
        } else {
            iMax = (i & 2) != 0 ? Math.max(i2, Math.min(iRound + i9, point.x)) : i9;
        }
        int i12 = i & 4;
        if (i12 != 0) {
            iMax2 = Math.max(i3, Math.min(i10 - iRound2, point.y));
        } else {
            iMax2 = (i & 8) != 0 ? Math.max(i3, Math.min(iRound2 + i10, point.y)) : i10;
        }
        float f5 = iMax / iMax2;
        if (z && ((z2 && f5 < 1.2f) || (!z2 && f5 > 0.8333333002196431d))) {
            if (z2) {
                int iMax5 = Math.max(i2, Math.min(point.x, iMax));
                iMax3 = Math.min(iMax2, Math.round(iMax5 / 1.2f));
                if (iMax3 < i3) {
                    iMax5 = Math.max(i2, Math.min(point.x, Math.round(i3 * 1.2f)));
                    iMax3 = i3;
                }
                iMax4 = Math.max(i3, Math.min(point.y, iMax2));
                int i13 = iMax5;
                iMin = Math.max(iMax, Math.round(iMax4 * 1.2f));
                if (iMin < i2) {
                    iMax4 = Math.max(i3, Math.min(point.y, Math.round(i2 / 1.2f)));
                    iMin = i2;
                }
                i4 = i13;
            } else {
                int iMax6 = Math.max(i2, Math.min(point.x, iMax));
                iMax3 = Math.max(iMax2, Math.round(iMax6 * 1.2f));
                if (iMax3 < i3) {
                    iMax6 = Math.max(i2, Math.min(point.x, Math.round(i3 / 1.2f)));
                    iMax3 = i3;
                }
                iMax4 = Math.max(i3, Math.min(point.y, iMax2));
                int i14 = iMax6;
                iMin = Math.min(iMax, Math.round(iMax4 / 1.2f));
                if (iMin < i2) {
                    iMax4 = Math.max(i3, Math.min(point.y, Math.round(i2 * 1.2f)));
                    iMin = i2;
                }
                i4 = i14;
            }
            if ((iMax > i9 || iMax2 > i10) == (i4 * iMax3 > iMin * iMax4)) {
                iMax = i4;
                iMax2 = iMax3;
            } else {
                iMax = iMin;
                iMax2 = iMax4;
            }
        }
        if (i11 != 0) {
            i5 = i7 - iMax;
        } else {
            i7 = i5 + iMax;
        }
        if (i12 != 0) {
            i6 = i8 - iMax2;
        } else {
            i8 = i6 + iMax2;
        }
        return new Rect(i5, i6, i7, i8);
    }
}
