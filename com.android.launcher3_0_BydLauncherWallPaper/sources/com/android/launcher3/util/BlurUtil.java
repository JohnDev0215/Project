package com.android.launcher3.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.Spanned;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes4.dex */
public final class BlurUtil {
    public static Bitmap doBlurByRender(Context context, Bitmap bitmap, float radius) {
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmap);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, allocationCreateFromBitmap.getElement());
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(radius);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap);
        allocationCreateFromBitmap.copyTo(bitmap);
        return bitmap;
    }

    public static Bitmap doBlur(Bitmap sentBitmap, int radius, Bitmap reuseBitmap) {
        int[] iArr;
        int i = radius;
        if (i < 1) {
            return null;
        }
        int width = sentBitmap.getWidth();
        int height = sentBitmap.getHeight();
        int i2 = width * height;
        int[] iArr2 = new int[i2];
        sentBitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
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
                iArr10[0] = (i26 & Spanned.SPAN_PRIORITY) >> 16;
                iArr10[1] = (i26 & 65280) >> 8;
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
                iArr12[0] = (i37 & Spanned.SPAN_PRIORITY) >> 16;
                iArr12[1] = (i37 & 65280) >> 8;
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
            height = i14;
            i4 = i27;
            iArr6 = iArr11;
        }
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
            int i63 = i42;
            int i64 = 0;
            while (i64 < i63) {
                iArr16[i62] = (iArr16[i62] & (-16777216)) | (iArr15[i55] << 16) | (iArr15[i56] << 8) | iArr15[i46];
                int i65 = i55 - i47;
                int i66 = i56 - i48;
                int i67 = i46 - i49;
                int[] iArr18 = iArr8[((i61 - i) + i45) % i45];
                int i68 = i47 - iArr18[0];
                int i69 = i48 - iArr18[1];
                int i70 = i49 - iArr18[2];
                if (i43 == 0) {
                    iArr14[i64] = Math.min(i64 + i10, i60) * i59;
                }
                int i71 = iArr14[i64] + i43;
                iArr18[0] = iArr3[i71];
                iArr18[1] = iArr4[i71];
                iArr18[2] = iArr5[i71];
                int i72 = i50 + iArr18[0];
                int i73 = i51 + iArr18[1];
                int i74 = i52 + iArr18[2];
                i55 = i65 + i72;
                i56 = i66 + i73;
                i46 = i67 + i74;
                i61 = (i61 + 1) % i45;
                int[] iArr19 = iArr8[i61];
                i47 = i68 + iArr19[0];
                i48 = i69 + iArr19[1];
                i49 = i70 + iArr19[2];
                i50 = i72 - iArr19[0];
                i51 = i73 - iArr19[1];
                i52 = i74 - iArr19[2];
                i62 += i59;
                i64++;
                i = radius;
            }
            i43++;
            i = radius;
            i41 = i60;
            i42 = i63;
            i5 = i45;
            iArr2 = iArr16;
            width = i59;
        }
        int i75 = width;
        reuseBitmap.setPixels(iArr2, 0, i75, 0, 0, i75, i42);
        return reuseBitmap;
    }
}
