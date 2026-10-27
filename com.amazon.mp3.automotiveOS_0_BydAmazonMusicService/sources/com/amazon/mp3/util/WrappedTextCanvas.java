package com.amazon.mp3.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public class WrappedTextCanvas extends Canvas {
    private static final Canvas sTextResizeCanvas = new Canvas();
    private int mMaxLines;

    public WrappedTextCanvas() {
        this.mMaxLines = Integer.MAX_VALUE;
    }

    public WrappedTextCanvas(Bitmap bitmap) {
        super(bitmap);
        this.mMaxLines = Integer.MAX_VALUE;
    }

    public void setMaxLines(int maxLines) {
        this.mMaxLines = maxLines;
    }

    private float findTextSize(String text, float nMinTextSize, float nMaxTextSize, int nMaxHeight, int nMaxWidth, Paint paint) {
        TextPaint textPaint = new TextPaint(paint);
        textPaint.setARGB(255, 255, 255, 255);
        for (Float fValueOf = Float.valueOf(nMaxTextSize); fValueOf.floatValue() >= nMinTextSize; fValueOf = Float.valueOf(fValueOf.floatValue() - 2.0f)) {
            textPaint.setTextSize(fValueOf.floatValue());
            StaticLayout staticLayout = new StaticLayout(text, 0, text.length(), textPaint, nMaxWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, nMaxWidth);
            staticLayout.draw(sTextResizeCanvas);
            if (staticLayout.getHeight() <= nMaxHeight) {
                boolean z = false;
                for (int i = 0; i < staticLayout.getLineCount(); i++) {
                    z |= staticLayout.getEllipsisCount(i) > 0;
                }
                if (!z) {
                    return fValueOf.floatValue();
                }
            }
        }
        return nMinTextSize;
    }

    public float drawWrappedText(String text, int topLine, int maxHeight, int maxWidth, float minTextSize, float maxTextSize, Paint paint, int nStrokeWidth, int smokeOpacity, int padding) {
        String str;
        StaticLayout staticLayout;
        float fFindTextSize = findTextSize(text, minTextSize, maxTextSize, maxHeight, maxWidth, paint);
        TextPaint textPaint = new TextPaint(paint);
        textPaint.setTextSize(fFindTextSize);
        StaticLayout staticLayout2 = new StaticLayout(text, 0, text.length(), textPaint, maxWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, maxWidth);
        int lineCount = staticLayout2.getLineCount();
        int i = this.mMaxLines;
        if (lineCount > i) {
            String strSubstring = text.substring(0, staticLayout2.getLineStart(i));
            str = strSubstring;
            staticLayout2 = new StaticLayout(strSubstring, 0, strSubstring.length(), textPaint, maxWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, maxWidth);
        } else {
            str = text;
        }
        int width = staticLayout2.getWidth();
        int iMin = Math.min(maxHeight, staticLayout2.getHeight());
        if (nStrokeWidth > 0) {
            TextPaint textPaint2 = new TextPaint(textPaint);
            textPaint2.setARGB(255, 0, 0, 0);
            textPaint2.setStyle(Paint.Style.STROKE);
            textPaint2.setStrokeWidth(nStrokeWidth);
            staticLayout = new StaticLayout(str, 0, str.length(), textPaint2, maxWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, maxWidth);
            width = staticLayout.getWidth();
            iMin = staticLayout.getHeight();
        } else {
            staticLayout = null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (staticLayout != null) {
            staticLayout.draw(canvas);
        }
        staticLayout2.draw(canvas);
        float height = getHeight();
        float width2 = getWidth();
        int i2 = padding * 2;
        int i3 = width + i2;
        int i4 = iMin + i2;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
        if (smokeOpacity > 0) {
            canvas2.drawARGB(smokeOpacity * 3, 0, 0, 0);
        }
        canvas2.drawBitmap(bitmapCreateBitmap, (width2 - width) / 2.0f, padding, (Paint) null);
        if (smokeOpacity > 0) {
            drawARGB(smokeOpacity, 0, 0, 0);
        }
        if (topLine == -1) {
            drawBitmap(bitmapCreateBitmap2, (width2 - i3) / 2.0f, (height - i4) / 2.0f, (Paint) null);
        } else if (topLine == -2) {
            drawBitmap(bitmapCreateBitmap2, (width2 - i3) / 2.0f, height - i4, (Paint) null);
        } else {
            drawBitmap(bitmapCreateBitmap2, (width2 - i3) / 2.0f, topLine, (Paint) null);
        }
        return i4;
    }
}
