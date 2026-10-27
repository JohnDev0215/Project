package com.android.launcher3.icons;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f1308a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f1309b = new Paint(3);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Paint f1310c = new Paint(3);
    private final BlurMaskFilter d;

    public h(int i) {
        this.f1308a = i;
        this.d = new BlurMaskFilter(this.f1308a * 0.010416667f, BlurMaskFilter.Blur.NORMAL);
    }

    public synchronized void a(Bitmap bitmap, BlurMaskFilter blurMaskFilter, int i, int i2, Canvas canvas) {
        this.f1309b.setMaskFilter(blurMaskFilter);
        Bitmap bitmapExtractAlpha = bitmap.extractAlpha(this.f1309b, new int[2]);
        this.f1310c.setAlpha(i);
        canvas.drawBitmap(bitmapExtractAlpha, r0[0], r0[1], this.f1310c);
        this.f1310c.setAlpha(i2);
        canvas.drawBitmap(bitmapExtractAlpha, r0[0], r0[1] + (this.f1308a * 0.020833334f), this.f1310c);
        this.f1310c.setAlpha(MediaControl.ACTION_NONE);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.f1310c);
    }

    public synchronized void b(Bitmap bitmap, Canvas canvas) {
        a(bitmap, this.d, 30, 61, canvas);
    }
}
