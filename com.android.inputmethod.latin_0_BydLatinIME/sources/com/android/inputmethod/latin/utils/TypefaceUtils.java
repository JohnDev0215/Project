package com.android.inputmethod.latin.utils;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes2.dex */
public final class TypefaceUtils {
    private static final char[] KEY_LABEL_REFERENCE_CHAR = {'M'};
    private static final char[] KEY_NUMERIC_HINT_LABEL_REFERENCE_CHAR = {'8'};
    private static final SparseArray<Float> sTextHeightCache = new SparseArray<>();
    private static final Rect sTextHeightBounds = new Rect();
    private static final SparseArray<Float> sTextWidthCache = new SparseArray<>();
    private static final Rect sTextWidthBounds = new Rect();
    private static final Rect sStringWidthBounds = new Rect();

    private TypefaceUtils() {
    }

    private static float getCharHeight(final char[] referenceChar, final Paint paint) {
        int charGeometryCacheKey = getCharGeometryCacheKey(referenceChar[0], paint);
        SparseArray<Float> sparseArray = sTextHeightCache;
        synchronized (sparseArray) {
            Float f = sparseArray.get(charGeometryCacheKey);
            if (f != null) {
                return f.floatValue();
            }
            Rect rect = sTextHeightBounds;
            paint.getTextBounds(referenceChar, 0, 1, rect);
            float fHeight = rect.height();
            sparseArray.put(charGeometryCacheKey, Float.valueOf(fHeight));
            return fHeight;
        }
    }

    private static float getCharWidth(final char[] referenceChar, final Paint paint) {
        int charGeometryCacheKey = getCharGeometryCacheKey(referenceChar[0], paint);
        SparseArray<Float> sparseArray = sTextWidthCache;
        synchronized (sparseArray) {
            Float f = sparseArray.get(charGeometryCacheKey);
            if (f != null) {
                return f.floatValue();
            }
            Rect rect = sTextWidthBounds;
            paint.getTextBounds(referenceChar, 0, 1, rect);
            float fWidth = rect.width();
            sparseArray.put(charGeometryCacheKey, Float.valueOf(fWidth));
            return fWidth;
        }
    }

    private static int getCharGeometryCacheKey(final char referenceChar, final Paint paint) {
        int textSize = (int) paint.getTextSize();
        Typeface typeface = paint.getTypeface();
        int i = referenceChar << 15;
        return typeface == Typeface.DEFAULT ? i + textSize : typeface == Typeface.DEFAULT_BOLD ? i + textSize + 4096 : typeface == Typeface.MONOSPACE ? i + textSize + 8192 : i + textSize;
    }

    public static float getReferenceCharHeight(final Paint paint) {
        return getCharHeight(KEY_LABEL_REFERENCE_CHAR, paint);
    }

    public static float getReferenceCharWidth(final Paint paint) {
        return getCharWidth(KEY_LABEL_REFERENCE_CHAR, paint);
    }

    public static float getReferenceDigitWidth(final Paint paint) {
        return getCharWidth(KEY_NUMERIC_HINT_LABEL_REFERENCE_CHAR, paint);
    }

    public static float getStringWidth(final String string, final Paint paint) {
        float fWidth;
        Rect rect = sStringWidthBounds;
        synchronized (rect) {
            paint.getTextBounds(string, 0, string.length(), rect);
            fWidth = rect.width();
        }
        return fWidth;
    }
}
