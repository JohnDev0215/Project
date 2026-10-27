package com.byd.bluetoothcall.view.azlist;

import android.graphics.Paint;
import android.text.TextPaint;

/* JADX INFO: compiled from: TextDrawUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static float a(float f, TextPaint textPaint, int i) {
        textPaint.setTextSize(i);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float f2 = fontMetrics.bottom;
        return (f + ((f2 - fontMetrics.top) / 2.0f)) - f2;
    }
}
