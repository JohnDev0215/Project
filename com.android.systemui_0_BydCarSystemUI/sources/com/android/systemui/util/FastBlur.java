package com.android.systemui.util;

import android.graphics.Bitmap;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
public class FastBlur {
    public static Bitmap blur(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        int[] iArr;
        int i = radius;
        Bitmap bitmapCopy = canReuseInBitmap ? sentBitmap : sentBitmap.copy(sentBitmap.getConfig(), true);
        if (i < 1) {
            return null;
        }
        int width = bitmapCopy.getWidth();
        int height = bitmapCopy.getHeight();
        int i2 = width * height;
        int[] iArr2 = new int[i2];
        bitmapCopy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i3 = width - 1;
        int i4 = height - 1;
        int i5 = i + i + 1;
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        int[] iArr5 = new int[i2];
        int[] iArr6 = new int[Math.max(width, height)];
        int i6 = (i5 + 1) >> 1;
        int i7 = i6 * i6;
        int i8 = i7 * 256;
        int[] iArr7 = new int[i8];
        for (int i9 = 0; i9 < i8; i9++) {
            iArr7[i9] = i9 / i7;
        }
        int[][] iArr8 = (int[][]) Array.newInstance((Class<?>) int.class, i5, 3);
        int i10 = i + 1;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i11 < height) {
            Bitmap bitmap = bitmapCopy;
            int i14 = height;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = -i;
            int i24 = 0;
            while (i23 <= i) {
                int i25 = i4;
                int[] iArr9 = iArr6;
                int i26 = iArr2[i12 + Math.min(i3, Math.max(i23, 0))];
                int[] iArr10 = iArr8[i23 + i];
                iArr10[0] = (i26 & 16711680) >> 16;
                iArr10[1] = (i26 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i26 & 255;
                int iAbs = i10 - Math.abs(i23);
                i24 += iArr10[0] * iAbs;
                i15 += iArr10[1] * iAbs;
                i16 += iArr10[2] * iAbs;
                if (i23 > 0) {
                    i20 += iArr10[0];
                    i21 += iArr10[1];
                    i22 += iArr10[2];
                } else {
                    i17 += iArr10[0];
                    i18 += iArr10[1];
                    i19 += iArr10[2];
                }
                i23++;
                i4 = i25;
                iArr6 = iArr9;
            }
            int i27 = i4;
            int[] iArr11 = iArr6;
            int i28 = i;
            int i29 = i24;
            int i30 = 0;
            while (i30 < width) {
                iArr3[i12] = iArr7[i29];
                iArr4[i12] = iArr7[i15];
                iArr5[i12] = iArr7[i16];
                int i31 = i29 - i17;
                int i32 = i15 - i18;
                int i33 = i16 - i19;
                int[] iArr12 = iArr8[((i28 - i) + i5) % i5];
                int i34 = i17 - iArr12[0];
                int i35 = i18 - iArr12[1];
                int i36 = i19 - iArr12[2];
                if (i11 == 0) {
                    iArr = iArr7;
                    iArr11[i30] = Math.min(i30 + i + 1, i3);
                } else {
                    iArr = iArr7;
                }
                int i37 = iArr2[i13 + iArr11[i30]];
                iArr12[0] = (i37 & 16711680) >> 16;
                iArr12[1] = (i37 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr12[2] = i37 & 255;
                int i38 = i20 + iArr12[0];
                int i39 = i21 + iArr12[1];
                int i40 = i22 + iArr12[2];
                i29 = i31 + i38;
                i15 = i32 + i39;
                i16 = i33 + i40;
                i28 = (i28 + 1) % i5;
                int[] iArr13 = iArr8[i28 % i5];
                i17 = i34 + iArr13[0];
                i18 = i35 + iArr13[1];
                i19 = i36 + iArr13[2];
                i20 = i38 - iArr13[0];
                i21 = i39 - iArr13[1];
                i22 = i40 - iArr13[2];
                i12++;
                i30++;
                iArr7 = iArr;
            }
            i13 += width;
            i11++;
            bitmapCopy = bitmap;
            height = i14;
            i4 = i27;
            iArr6 = iArr11;
        }
        Bitmap bitmap2 = bitmapCopy;
        int i41 = i4;
        int[] iArr14 = iArr6;
        int i42 = height;
        int[] iArr15 = iArr7;
        int i43 = 0;
        while (i43 < width) {
            int i44 = -i;
            int i45 = i5;
            int[] iArr16 = iArr2;
            int i46 = 0;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = i44;
            int i54 = i44 * width;
            int i55 = 0;
            int i56 = 0;
            while (i53 <= i) {
                int i57 = width;
                int iMax = Math.max(0, i54) + i43;
                int[] iArr17 = iArr8[i53 + i];
                iArr17[0] = iArr3[iMax];
                iArr17[1] = iArr4[iMax];
                iArr17[2] = iArr5[iMax];
                int iAbs2 = i10 - Math.abs(i53);
                i55 += iArr3[iMax] * iAbs2;
                i56 += iArr4[iMax] * iAbs2;
                i46 += iArr5[iMax] * iAbs2;
                if (i53 > 0) {
                    i50 += iArr17[0];
                    i51 += iArr17[1];
                    i52 += iArr17[2];
                } else {
                    i47 += iArr17[0];
                    i48 += iArr17[1];
                    i49 += iArr17[2];
                }
                int i58 = i41;
                if (i53 < i58) {
                    i54 += i57;
                }
                i53++;
                i41 = i58;
                width = i57;
            }
            int i59 = width;
            int i60 = i41;
            int i61 = i;
            int i62 = i43;
            int i63 = i56;
            int i64 = i42;
            int i65 = i55;
            int i66 = 0;
            while (i66 < i64) {
                iArr16[i62] = (iArr16[i62] & ViewCompat.MEASURED_STATE_MASK) | (iArr15[i65] << 16) | (iArr15[i63] << 8) | iArr15[i46];
                int i67 = i65 - i47;
                int i68 = i63 - i48;
                int i69 = i46 - i49;
                int[] iArr18 = iArr8[((i61 - i) + i45) % i45];
                int i70 = i47 - iArr18[0];
                int i71 = i48 - iArr18[1];
                int i72 = i49 - iArr18[2];
                if (i43 == 0) {
                    iArr14[i66] = Math.min(i66 + i10, i60) * i59;
                }
                int i73 = iArr14[i66] + i43;
                iArr18[0] = iArr3[i73];
                iArr18[1] = iArr4[i73];
                iArr18[2] = iArr5[i73];
                int i74 = i50 + iArr18[0];
                int i75 = i51 + iArr18[1];
                int i76 = i52 + iArr18[2];
                i65 = i67 + i74;
                i63 = i68 + i75;
                i46 = i69 + i76;
                i61 = (i61 + 1) % i45;
                int[] iArr19 = iArr8[i61];
                i47 = i70 + iArr19[0];
                i48 = i71 + iArr19[1];
                i49 = i72 + iArr19[2];
                i50 = i74 - iArr19[0];
                i51 = i75 - iArr19[1];
                i52 = i76 - iArr19[2];
                i62 += i59;
                i66++;
                i = radius;
            }
            i43++;
            i = radius;
            i41 = i60;
            i42 = i64;
            i5 = i45;
            iArr2 = iArr16;
            width = i59;
        }
        int i77 = width;
        bitmap2.setPixels(iArr2, 0, i77, 0, 0, i77, i42);
        return bitmap2;
    }
}
