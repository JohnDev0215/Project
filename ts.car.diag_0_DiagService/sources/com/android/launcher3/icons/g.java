package com.android.launcher3.icons;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f1305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Bitmap f1306b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Canvas f1307c;
    private final Paint d;
    private final Paint e;
    private final byte[] f;
    private final RectF g;
    private float h;
    private boolean i;
    private final float[] j;
    private final float[] k;
    private final Rect l;
    private final Path m;
    private final Matrix n;

    g(Context context, int i, boolean z) {
        int i2 = i * 2;
        this.f1305a = i2;
        this.f1306b = Bitmap.createBitmap(i2, i2, Bitmap.Config.ALPHA_8);
        this.f1307c = new Canvas(this.f1306b);
        int i3 = this.f1305a;
        this.f = new byte[i3 * i3];
        this.j = new float[i3];
        this.k = new float[i3];
        this.l = new Rect();
        this.g = new RectF();
        Paint paint = new Paint();
        this.d = paint;
        paint.setColor(-65536);
        this.d.setStyle(Paint.Style.FILL);
        this.d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        Paint paint2 = new Paint();
        this.e = paint2;
        paint2.setStrokeWidth(context.getResources().getDisplayMetrics().density * 2.0f);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setColor(-16777216);
        this.e.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.m = new Path();
        this.n = new Matrix();
        this.h = 0.0f;
        this.i = z;
    }

    private static void a(float[] fArr, int i, int i2, int i3) {
        float[] fArr2 = new float[fArr.length - 1];
        int i4 = -1;
        float f = Float.MAX_VALUE;
        for (int i5 = i2 + 1; i5 <= i3; i5++) {
            if (fArr[i5] > -1.0f) {
                if (f == Float.MAX_VALUE) {
                    i4 = i2;
                } else {
                    float f2 = ((fArr[i5] - fArr[i4]) / (i5 - i4)) - f;
                    float f3 = i;
                    if (f2 * f3 < 0.0f) {
                        while (i4 > i2) {
                            i4--;
                            if ((((fArr[i5] - fArr[i4]) / (i5 - i4)) - fArr2[i4]) * f3 >= 0.0f) {
                                break;
                            }
                        }
                    }
                }
                f = (fArr[i5] - fArr[i4]) / (i5 - i4);
                for (int i6 = i4; i6 < i5; i6++) {
                    fArr2[i6] = f;
                    fArr[i6] = fArr[i4] + ((i6 - i4) * f);
                }
                i4 = i5;
            }
        }
    }

    private static float b(float f, float f2, float f3) {
        float f4 = f / f2;
        if (f / f3 > (f4 < 0.7853982f ? 0.6597222f : ((1.0f - f4) * 0.040449437f) + 0.6510417f)) {
            return (float) Math.sqrt(r4 / r3);
        }
        return 1.0f;
    }

    private boolean d(Path path) {
        if (Math.abs((this.l.width() / this.l.height()) - 1.0f) > 0.05f) {
            return false;
        }
        this.n.reset();
        this.n.setScale(this.l.width(), this.l.height());
        Matrix matrix = this.n;
        Rect rect = this.l;
        matrix.postTranslate(rect.left, rect.top);
        path.transform(this.n, this.m);
        this.f1307c.drawPath(this.m, this.d);
        this.f1307c.drawPath(this.m, this.e);
        return e();
    }

    private boolean e() {
        Rect rect;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f);
        byteBufferWrap.rewind();
        this.f1306b.copyPixelsToBuffer(byteBufferWrap);
        Rect rect2 = this.l;
        int i = rect2.top;
        int i2 = this.f1305a;
        int i3 = i * i2;
        int i4 = i2 - rect2.right;
        int i5 = 0;
        while (true) {
            rect = this.l;
            if (i >= rect.bottom) {
                break;
            }
            int i6 = rect.left;
            int i7 = i3 + i6;
            while (i6 < this.l.right) {
                if ((this.f[i7] & 255) > 40) {
                    i5++;
                }
                i7++;
                i6++;
            }
            i3 = i7 + i4;
            i++;
        }
        return ((float) i5) / ((float) (rect.width() * this.l.height())) < 0.005f;
    }

    @TargetApi(26)
    public static float f(Drawable drawable, int i, @Nullable RectF rectF) {
        Rect rect = new Rect(drawable.getBounds());
        drawable.setBounds(0, 0, i, i);
        Path iconMask = ((AdaptiveIconDrawable) drawable).getIconMask();
        Region region = new Region();
        region.setPath(iconMask, new Region(0, 0, i, i));
        Rect bounds = region.getBounds();
        int iA = e.a(region);
        if (rectF != null) {
            float f = i;
            rectF.set(bounds.left / f, bounds.top / f, 1.0f - (bounds.right / f), 1.0f - (bounds.bottom / f));
        }
        drawable.setBounds(rect);
        float f2 = iA;
        return b(f2, f2, i * i);
    }

    public synchronized float c(@NonNull Drawable drawable, @Nullable RectF rectF, @Nullable Path path, @Nullable boolean[] zArr) {
        if (b.p && (drawable instanceof AdaptiveIconDrawable)) {
            if (this.h == 0.0f) {
                this.h = f(drawable, this.f1305a, this.g);
            }
            if (rectF != null) {
                rectF.set(this.g);
            }
            return this.h;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            if (intrinsicWidth <= 0 || intrinsicWidth > this.f1305a) {
                intrinsicWidth = this.f1305a;
            }
            if (intrinsicHeight <= 0 || intrinsicHeight > this.f1305a) {
                intrinsicHeight = this.f1305a;
            }
        } else if (intrinsicWidth > this.f1305a || intrinsicHeight > this.f1305a) {
            int iMax = Math.max(intrinsicWidth, intrinsicHeight);
            intrinsicWidth = (this.f1305a * intrinsicWidth) / iMax;
            intrinsicHeight = (this.f1305a * intrinsicHeight) / iMax;
        }
        int i = 0;
        this.f1306b.eraseColor(0);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(this.f1307c);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.f);
        byteBufferWrap.rewind();
        this.f1306b.copyPixelsToBuffer(byteBufferWrap);
        int iMin = this.f1305a + 1;
        int i2 = this.f1305a - intrinsicWidth;
        int i3 = 0;
        int i4 = 0;
        int i5 = -1;
        int iMax2 = -1;
        int i6 = -1;
        while (i3 < intrinsicHeight) {
            int i7 = -1;
            int i8 = -1;
            for (int i9 = i; i9 < intrinsicWidth; i9++) {
                if ((this.f[i4] & 255) > 40) {
                    if (i8 == -1) {
                        i8 = i9;
                    }
                    i7 = i9;
                }
                i4++;
            }
            i4 += i2;
            this.j[i3] = i8;
            this.k[i3] = i7;
            if (i8 != -1) {
                if (i5 == -1) {
                    i5 = i3;
                }
                iMin = Math.min(iMin, i8);
                iMax2 = Math.max(iMax2, i7);
                i6 = i3;
            }
            i3++;
            i = 0;
        }
        if (i5 != -1 && iMax2 != -1) {
            a(this.j, 1, i5, i6);
            a(this.k, -1, i5, i6);
            float f = 0.0f;
            for (int i10 = 0; i10 < intrinsicHeight; i10++) {
                if (this.j[i10] > -1.0f) {
                    f += (this.k[i10] - this.j[i10]) + 1.0f;
                }
            }
            this.l.left = iMin;
            this.l.right = iMax2;
            this.l.top = i5;
            this.l.bottom = i6;
            if (rectF != null) {
                float f2 = intrinsicWidth;
                float f3 = intrinsicHeight;
                rectF.set(this.l.left / f2, this.l.top / f3, 1.0f - (this.l.right / f2), 1.0f - (this.l.bottom / f3));
            }
            if (zArr != null && this.i && zArr.length > 0) {
                zArr[0] = d(path);
            }
            return b(f, ((i6 + 1) - i5) * ((iMax2 + 1) - iMin), intrinsicWidth * intrinsicHeight);
        }
        return 1.0f;
    }
}
